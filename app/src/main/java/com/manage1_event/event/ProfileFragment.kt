package com.manage1_event.event

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.firestore.FirebaseFirestore
import com.manage1_event.event.databinding.FragmentProfileBinding
import kotlinx.android.synthetic.main.activity_login_page.*


class ProfileFragment : Fragment() {

    private lateinit var db: FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentProfileBinding.inflate(inflater, container, false)

        val sharedPref=this.requireActivity().getPreferences(Context.MODE_PRIVATE)
        val isLogin=sharedPref!!.getString("Email","1")

        binding.logout.setOnClickListener {
            sharedPref.edit().remove("Email").apply()
            var intent = Intent(this.activity,MainActivity::class.java)
            startActivity(intent)
            this.requireActivity().finish()
        }

        if(isLogin=="1")
        {
            var email = this.requireActivity().intent.getStringExtra("email")

            if(email!=null)
            {
                setText(email)
                with(sharedPref.edit())
                {
                    putString("Email",email)
                    apply()
                }
            }

            else{
                var intent = Intent(this.activity,MainActivity::class.java)
                startActivity(intent)
                this.requireActivity().finish()
            }
        }
        else
        {
            setText(isLogin)
        }


        return binding.root
    }

    private fun setText(email:String?)
    {
        db= FirebaseFirestore.getInstance()
        if (email != null) {
            db.collection("USERS").document(email).get()
                .addOnSuccessListener {
                        tasks->
                    name.text=tasks.get("Name").toString()
                    phone.text=tasks.get("Phone").toString()
                    emailLog.text=tasks.get("email").toString()

                }
        }

    }



    }
