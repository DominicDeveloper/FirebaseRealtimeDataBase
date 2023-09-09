package com.asadbek.firebaserealtimedatabase

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.asadbek.firebaserealtimedatabase.databinding.ActivityMainBinding
import com.google.firebase.database.*

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var firebaseDatabase: FirebaseDatabase
    lateinit var reference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseDatabase = FirebaseDatabase.getInstance()
        reference = firebaseDatabase.getReference("kontaktlar")


        binding.btnSend.setOnClickListener {
            val name = binding.edtName.text.toString()
            val number = binding.edtNumber.text.toString()

            val kontaktlar = Kontaktlar(reference.push().key, name, number)
            reference.child(reference.push().key!!).setValue(kontaktlar).addOnSuccessListener {
                Toast.makeText(this, "yuborildi!", Toast.LENGTH_SHORT).show()
            }

        }

        binding.btnGet.setOnClickListener {
            reference.addValueEventListener(object : ValueEventListener {
                @SuppressLint("SetTextI18n")
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (child in snapshot.children) {
                        val kontaktlar:Kontaktlar? = child.getValue(Kontaktlar::class.java)
                        if (kontaktlar != null){
                            binding.txt.text = kontaktlar.name + "\n" + kontaktlar.number
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {

                }
            })

        }

    }
}