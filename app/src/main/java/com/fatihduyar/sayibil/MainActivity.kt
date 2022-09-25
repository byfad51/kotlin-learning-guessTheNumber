package com.fatihduyar.sayibil

import android.content.DialogInterface
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var sharedPreferences :SharedPreferences
    var toplamoy : Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sharedPreferences = this.getSharedPreferences("com.fatihduyar.sayibil", MODE_PRIVATE)
       toplamoy = sharedPreferences.getInt("toplamoyun",0)

        if(toplamoy !=null){
            toplamoyun.text = "Toplam Oyun: ${toplamoy}"
        }else{
            toplamoy = 0
            toplamoyun.text = "Toplam Oyun: ${toplamoy}"
        }
    }
    private var dogrusay = 0
    private var yanlissay = 0
    private var sonuc = 0
    var cevap = 0
    var rand = Random()
    fun gonder ( view : View){


        val sayi1 = rand.nextInt(100)
        val sayi2 = rand.nextInt(100)
        var mesaj = AlertDialog.Builder(this)
        mesaj.setTitle("Tahmin etmece.")
        mesaj.setMessage("Sizce sayı ne?")


        var dogru = rand.nextBoolean()

        if(dogru){
            sonuc = sayi1
        }else {
            sonuc = sayi2
        }

        mesaj.setPositiveButton(sayi1.toString(), DialogInterface.OnClickListener { dialog, which ->
            cevap = sayi1
            if(sonuc == cevap){
                dogrusay++
                dogruSayisi.text = "Doğru Sayısı: ${dogrusay}"
                sonucmesaj.text = "${cevap} doğru tahmin."
            }else{
                yanlissay++
                yanlisSayisi.text = "Yanlış Sayısı: ${yanlissay}"
                sonucmesaj.text = "${cevap} yanlış tahmin."
            }
        })
        mesaj.setNegativeButton(sayi2.toString(), DialogInterface.OnClickListener { dialog, which ->
            cevap = sayi2
            if(sonuc == cevap){
                dogrusay++
                dogruSayisi.text = "Doğru Sayısı: ${dogrusay}"
                sonucmesaj.text = "${cevap} doğru tahmin."
            }else{
                yanlissay++
                yanlisSayisi.text = "Yanlış Sayısı: ${yanlissay}"
                sonucmesaj.text = "${cevap} yanlış tahmin."
            }
        })


        mesaj.show()
        sharedPreferences.edit().putInt("toplamoyun", (toplamoy!! + 1)).apply()
        toplamoy = sharedPreferences.getInt("toplamoyun",0)

        if(toplamoy !=null){
            toplamoyun.text = "Toplam Oyun: ${toplamoy}"
        }
    }
}