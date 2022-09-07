package com.example.ledger
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.Charset
class MainActivity : AppCompatActivity() {

    private var Date: ArrayList<String> = ArrayList()
    private var Particular: ArrayList<String> = ArrayList()
    private var Amount: ArrayList<String> = ArrayList()
    private var Dr: ArrayList<String> = ArrayList()
    private var Cr: ArrayList<String> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView = findViewById<RecyclerView>(R.id.Ledger)
        val linearLayoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = linearLayoutManager
        try {
            val obj = JSONObject(loadJSONFromAsset())
            val userArray = obj.getJSONArray("message")
            for (i in 0 until userArray.length()) {
                val userDetail = userArray.getJSONObject(i)
                Date.add(userDetail.getString("journal_date"))
                Particular.add(userDetail.getString("by_to_account_name"))
                Amount.add(userDetail.getString("running_balance"))
                Dr.add(userDetail.getString("dr_amount"))
                Cr.add(userDetail.getString("cr_amount"))

            }
        }
        catch (e: JSONException) {
            e.printStackTrace()
        }
        val customAdapter = CustomAdapter(this@MainActivity, Date,Particular,Amount,Dr,Cr)
        recyclerView.adapter = customAdapter
    }

    private fun loadJSONFromAsset(): String {
        val json: String?
        try {
            val inputStream = assets.open("Ledger.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            val charset: Charset = Charsets.UTF_8
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer, charset)
        }
        catch (ex: IOException) {
            ex.printStackTrace()
            return ""
        }
        return json
    }
}