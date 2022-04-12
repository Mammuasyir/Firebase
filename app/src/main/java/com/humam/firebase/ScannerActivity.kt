package com.humam.firebase

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.budiyev.android.codescanner.AutoFocusMode
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.DecodeCallback
import com.budiyev.android.codescanner.ErrorCallback
import com.budiyev.android.codescanner.ScanMode
import kotlinx.android.synthetic.main.activity_scanner.*

class ScannerActivity : AppCompatActivity() {
    private lateinit var codeScan: CodeScanner
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scanner)
        setPermission()
        codeScanner()
    }

    private fun codeScanner() {
        codeScan =CodeScanner(this , scanner)
        codeScan.apply {
            camera = CodeScanner.CAMERA_BACK
            formats =CodeScanner.ALL_FORMATS
            autoFocusMode = AutoFocusMode.SAFE
            scanMode = ScanMode.CONTINUOUS
            isFlashEnabled = false

            decodeCallback = DecodeCallback {
                runOnUiThread {
                    tv_url_output.text = it.text

                }
            }

            errorCallback = ErrorCallback {
                runOnUiThread {
                    Toast.makeText(applicationContext, "${it.message}", Toast.LENGTH_SHORT).show()
                }
            }

            scanner.setOnClickListener {
                codeScan.startPreview()
            }
        }
    }

    private fun setPermission() {
        val permission = ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)
        if (permission != PackageManager.PERMISSION_GRANTED) {
            makeReq()
        }    }

    private fun makeReq() {
        ActivityCompat.requestPermissions(
            this, arrayOf(android.Manifest.permission.CAMERA), CAMREQ
        )
    }

    override fun onResume() {
        super.onResume()
        codeScan.startPreview()
    }

    override fun onPause() {
        codeScan.releaseResources()
        super.onPause()

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            CAMREQ ->{
                if (grantResults.isEmpty()|| grantResults[0] != PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this, "Perizinann dibutuhkan !!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    companion object{
        private const val CAMREQ = 101
    }
}