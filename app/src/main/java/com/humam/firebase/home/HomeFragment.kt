package com.humam.firebase.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.humam.firebase.R
import com.humam.firebase.adapter.AdapterProduk
import com.humam.firebase.adapter.AdapterSlider
import com.humam.firebase.databinding.FragmentHomeBinding
import com.humam.firebase.model.ModelProduk

class HomeFragment : Fragment() {

    private var binding: FragmentHomeBinding? = null
    lateinit var rvBaju: RecyclerView
    lateinit var vpSlider: ViewPager
    lateinit var rvbuku: RecyclerView


    // This property is only valid between onCreateView and
    // onDestroyView. private val binding get() = binding!!

    override fun onCreateView(

        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


//        ilangin biru2 di notif bar
        (requireActivity() as AppCompatActivity).window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

//        (requireActivity() as AppCompatActivity).supportActionBar?.

//buat ilangin action bar
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()
        val view: View = inflater.inflate(R.layout.fragment_home, container, false)
        vpSlider = view.findViewById(R.id.vp_slider)
        val arrSlider = ArrayList<Int>()
        arrSlider.add(R.drawable.carousel2)
        arrSlider.add(R.drawable.carousel3)
        arrSlider.add(R.drawable.carousel4)


        val adapterSLider = AdapterSlider(arrSlider, activity)
        vpSlider.adapter = adapterSLider

        rvBaju = view.findViewById(R.id.rv_baju)
        val adapterBaju = AdapterProduk(arrayBaju)
        val lm = LinearLayoutManager(activity)
        lm.orientation = LinearLayoutManager.HORIZONTAL
        rvBaju.setHasFixedSize(true)
        rvBaju.layoutManager = lm
        rvBaju.adapter = adapterBaju

        rvbuku = view.findViewById(R.id.rv_buku)
        val adapterBuku = AdapterProduk(arrayBuku)
        val lmb = LinearLayoutManager(activity)
        lmb.orientation = LinearLayoutManager.HORIZONTAL
        rvbuku.setHasFixedSize(true)
        rvbuku.layoutManager = lmb
        rvbuku.adapter = adapterBuku

        return view
    }

    val arrayBaju: ArrayList<ModelProduk>
        get() {

            val arrBj = ArrayList<ModelProduk>()
            val produkBaju1 = ModelProduk()
            produkBaju1.namaProduk = "Baju VMWare"
            produkBaju1.hargaProduk = "RP. 99,000,00"
            produkBaju1.gambarProduk = R.drawable.baju_1

            val produkBaju2 = ModelProduk()
            produkBaju2.namaProduk = "Baju Kedua"
            produkBaju2.hargaProduk = "RP. 200,000,00"
            produkBaju2.gambarProduk = R.drawable.baju_2

            val produkBaju3 = ModelProduk()
            produkBaju3.namaProduk = "jaket ketiga"
            produkBaju3.hargaProduk = "RP. 500,000,00"
            produkBaju3.gambarProduk = R.drawable.jaket_1

            val produkBaju4 = ModelProduk()
            produkBaju4.namaProduk = "jaket keempat"
            produkBaju4.hargaProduk = "RP. 1000,000,00"
            produkBaju4.gambarProduk = R.drawable.jaket_2

            val produkBaju5 = ModelProduk()
            produkBaju5.namaProduk = "jaket lima"
            produkBaju5.hargaProduk = "RP. 500,000,00"
            produkBaju5.gambarProduk = R.drawable.jaket_2

            val produkBaju6 = ModelProduk()
            produkBaju6.namaProduk = "jaket keeenammpat"
            produkBaju6.hargaProduk = "RP. 1000,000,00"
            produkBaju6.gambarProduk = R.drawable.jaket_1

            arrBj.add(produkBaju1)
            arrBj.add(produkBaju2)
            arrBj.add(produkBaju3)
            arrBj.add(produkBaju4)
            arrBj.add(produkBaju5)
            arrBj.add(produkBaju6)


            return arrBj

        }
    val arrayBuku: ArrayList<ModelProduk>
        get() {

            val arrBk = ArrayList<ModelProduk>()
            val produkBuku1 = ModelProduk()
            produkBuku1.namaProduk = "buku VMWare"
            produkBuku1.hargaProduk = "RP. 99,000,00"
            produkBuku1.gambarProduk = R.drawable.buku_1

            val produkBuku2 = ModelProduk()
            produkBuku2.namaProduk = "Baju haha"
            produkBuku2.hargaProduk = "RP. 99,000,00"
            produkBuku2.gambarProduk = R.drawable.buku_2

            val produkBuku4 = ModelProduk()
            produkBuku4.namaProduk = "buku VMWare"
            produkBuku4.hargaProduk = "RP. 99,000,00"
            produkBuku4.gambarProduk = R.drawable.buku_4




            arrBk.add(produkBuku1)
            arrBk.add(produkBuku2)
            arrBk.add(produkBuku4)


            return arrBk

        }

    override fun onDestroyView() {

        super.onDestroyView()
        binding = null
    }
}



