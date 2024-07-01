package com.example.akilisawa.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.akilisawa.R
import com.example.akilisawa.data.DataStoreManager
import com.example.akilisawa.databinding.FragmentRegistrationBinding
import com.example.akilisawa.model.ClientTokenResponse
import com.example.akilisawa.network.ApiService
import com.example.akilisawa.network.RestClient
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegistrationFragment : Fragment() {

    private var _binding: FragmentRegistrationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
    }

    private fun setupViews() {
        with(binding) {
            backButton.setOnClickListener {
                findNavController().navigateUp()
            }

            userTypeToggle.addOnButtonCheckedListener { _, checkedId, isChecked ->
                if (isChecked) {
                    when (checkedId) {
                        R.id.patientButton -> {
                            // Handle patient selection
                        }

                        R.id.healthcareWorkerButton -> {
                            // Handle healthcare worker selection
                        }
                    }
                }
            }

            continueButton.setOnClickListener {
                if (validateInputs()) {
                    registerUser()
                }
            }
        }
    }

    private fun validateInputs(): Boolean {
        with(binding) {
            val firstName = firstNameLayout.editText?.text.toString().trim()
            val lastName = lastNameLayout.editText?.text.toString().trim()
            val email = emailLayout.editText?.text.toString().trim()
            val phone = phoneLayout.editText?.text.toString().trim()
            val idNumber = idNumberLayout.editText?.text.toString().trim()

            if (firstName.isEmpty()) {
                firstNameLayout.error = "First name is required"
                return false
            }
            if (lastName.isEmpty()) {
                lastNameLayout.error = "Last name is required"
                return false
            }
            if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                emailLayout.error = "Valid email is required"
                return false
            }
            if (phone.isEmpty()) {
                phoneLayout.error = "Phone number is required"
                return false
            }
            if (idNumber.isEmpty()) {
                idNumberLayout.error = "ID number is required"
                return false
            }
            if (!termsCheckbox.isChecked) {
                Toast.makeText(
                    context,
                    "Please accept the terms and conditions",
                    Toast.LENGTH_SHORT
                ).show()
                return false
            }

            // Clear any previous errors
            firstNameLayout.error = null
            lastNameLayout.error = null
            emailLayout.error = null
            phoneLayout.error = null
            idNumberLayout.error = null

            return true
        }
    }

    private fun registerUser() {
        with(binding) {
            val userType =
                if (userTypeToggle.checkedButtonId == R.id.patientButton) "Patient" else "Healthcare Worker"
            val firstName = firstNameLayout.editText?.text.toString().trim()
            val lastName = lastNameLayout.editText?.text.toString().trim()
            val email = emailLayout.editText?.text.toString().trim()
            val phone = phoneLayout.editText?.text.toString().trim()
            val idNumber = idNumberLayout.editText?.text.toString().trim()

            val apiService = RestClient.retrofit.create(ApiService::class.java)
            val call = apiService.getClientToken("password", phone, idNumber)

            call.enqueue(object : Callback<ClientTokenResponse> {
                override fun onResponse(
                    call: Call<ClientTokenResponse>,
                    response: Response<ClientTokenResponse>
                ) {
                    if (response.isSuccessful) {
                        // Handle successful response
                        val clientTokenResponse = response.body()
                        if (clientTokenResponse != null) {
                            val accessToken = clientTokenResponse.access_token
                            val refreshToken = clientTokenResponse.refresh_token

                            // Assuming you have a context available. If not, you might need to pass it in or find another way to get it.
                            val dataStoreManager = DataStoreManager(requireContext())

                            // Save the access token and refresh token using your DataStoreManager
                            lifecycleScope.launch {
                                dataStoreManager.saveAccessToken(accessToken)
                                dataStoreManager.saveRefreshToken(refreshToken)
                            }
                        }

                        // TODO: Navigate to next screen
                    } else {
                        // Handle error response
                        Toast.makeText(
                            context,
                            "Registration failed: ${response.errorBody()?.string()}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<ClientTokenResponse>, t: Throwable) {
                    // Handle failure
                    Toast.makeText(context, "Registration failed: ${t.message}", Toast.LENGTH_SHORT)
                        .show()
                }
            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}