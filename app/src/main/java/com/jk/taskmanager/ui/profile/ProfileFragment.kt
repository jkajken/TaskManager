package com.jk.taskmanager.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.jk.taskmanager.data.local.Pref
import com.jk.taskmanager.databinding.FragmentProfileBinding
import com.squareup.picasso.Picasso


class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var pref: Pref
    private val getContent =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            // Handle the returned Uri
            uri?.let { it ->
                // The image was saved into the given Uri -> do something with it
                Picasso.with(context).load(it).resize(800, 800).into(binding.profileImage)
                pref.saveImage(uri.toString())
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.profileImage.setOnClickListener {
            getContent.launch("image/*")

            pref = Pref(requireContext())

            binding.etName2.setText(pref.getName())
            binding.etName2.addTextChangedListener {
                pref.saveName(binding.etName2.text.toString())


                binding.etAge2.setText(pref.getAge()).toString()
                binding.etAge2.addTextChangedListener {
                    pref.saveAge(binding.etAge2.text.toString())

                    Picasso.with(context).load(pref.getImage())
                        .resize(800, 800)
                        .into(binding.profileImage)


                }
            }
        }
    }
}
