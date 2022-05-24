package com.ibnu.exitkomputerstore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_item.*
import kotlinx.android.synthetic.main.fragment_item.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ItemActivity : AppCompatActivity() {

    private lateinit var itemAdapter: ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item)

        setupRv()
        getDataFromApi()
    }

    private fun setupRv() {
        itemAdapter = ItemAdapter(arrayListOf(), object : ItemAdapter.OnAdapterListener {
            override fun onClick(result: ItemModel.Result) {
                Toast.makeText(applicationContext, result.nama, Toast.LENGTH_SHORT).show()

                btn_frItem_addCart.setOnClickListener{



                    Toast.makeText(applicationContext, "Berhasil ditambah", Toast.LENGTH_SHORT).show()
                }

//                startActivity(
//                    Intent(this@ItemActivity, ItemActivity::class.java)
//                        .putExtra("intent_id", result.id.toString())
//                        .putExtra("intent_image", result.image)
//                        .putExtra("intent_nama", result.nama)
//                        .putExtra("intent_deskripsi", result.deskripsi)
//                        .putExtra("intent_harga", "Rp. ${result.harga}")
//                )
            }
        })

        rv_activity_item.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = itemAdapter
        }
    }

    private fun getDataFromApi() {
        ApiService.endPoint.getItem().enqueue(object : Callback<ItemModel> {
            override fun onResponse(call: Call<ItemModel>, response: Response<ItemModel>) {
                if (response.isSuccessful) {
                    showLoading(false)

                    showResult(response.body()!!)
                } else {
                    showLoading(true)
                }
            }

            override fun onFailure(call: Call<ItemModel>, t: Throwable) {
                showLoading(false)
            }
        })
    }

    private fun showLoading(loading: Boolean) {
        when (loading) {
            true -> pb_activity_item.visibility = View.VISIBLE
            false -> pb_activity_item.visibility = View.GONE
        }
    }

    private fun showResult(results: ItemModel) {
        itemAdapter.setItem(results.result)
    }
}