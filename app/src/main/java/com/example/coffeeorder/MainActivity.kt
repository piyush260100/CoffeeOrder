package com.example.coffeeorder

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {
    lateinit var order: Button
    lateinit var amt: TextView
    lateinit var Ncoffee: TextView
    lateinit var price:TextView
    lateinit var WhippedCream:CheckBox
    lateinit var ExtraChocolate:CheckBox
    lateinit var name:EditText

    var whippedcream=false
    var extrachocolate=false
    var k=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        order=findViewById(R.id.Order)
        amt=findViewById(R.id.amt)
        Ncoffee=findViewById(R.id.Ncoffee)
        price=findViewById(R.id.price)
        WhippedCream=findViewById<CheckBox>(R.id.whippedCream)
        ExtraChocolate=findViewById<CheckBox>(R.id.extraChocolate)
        name=findViewById(R.id.name)
        var numberofcoffee:Int=0
        //price(numberofcoffee)
        display(numberofcoffee)

    }


    fun coffeeorder(androidView: View) {

        amt.text = orderSummary(price(Ncoffee.text.toString().toInt(),whippedcream,extrachocolate))
        price.text="ORDER SUMMARY"
        order.text="ORDER"
        if(k>0)
        {
            composeEmail("Coffee Order Summary for ${name.text.toString()}", amt.text.toString())
        }
        k++
        Log.i("k :",k.toString())
    }

    fun increase(androidView: View) {

        val n=Ncoffee.text.toString()
        var m=n.toInt()
        m=m+1
        display(m)

    }

    fun decrease(androidView: View)
    {
        val n=Ncoffee.text.toString()
        var m=n.toInt()
        if(m>0)
        { m=m-1}
        else
        {m=0}
        display(m)

    }

    fun display(numberofcoffee:Int)
    {
        Ncoffee.text=numberofcoffee.toString()
    }

    fun price(numberofcoffee:Int,whippedcream:Boolean,extrachocolate:Boolean):Int
    {
        var total=0
        if(whippedcream==true && extrachocolate==true)
        {
            total= numberofcoffee * 90
        }
        else if((whippedcream==true && extrachocolate==false) || (whippedcream==false && extrachocolate==true))
        {
            total=numberofcoffee * 70
        }
        else
        {
            total=numberofcoffee * 50
        }
        return total
    }

    fun orderSummary(amt:Int):String
    {
        var customer=name.text.toString()
        var wc=whippedcream
        var ec=extrachocolate
        var wc1="No"
        var ec1="No"
        if(wc==true)
        {
            wc1="Yes"
        }
        else
        {
            wc1="No"
        }
        if(ec==true)
        {
            ec1="Yes"
        }
        else
        {
            ec1="No"
        }
        var q=Ncoffee.text.toString()
        var p=price(Ncoffee.text.toString().toInt(),whippedcream,extrachocolate)
        var message="Name : $customer\nQuantity : $q\nAdd Whipped Cream : $wc1\nAdd Extra Chocolate : $ec1\nRs: $p\nThank You!!"

        return message
    }

    fun WhippedCream(androidView: View)
    {
        if(WhippedCream.isChecked==true) {

            whippedcream = true
        }
        else {
            whippedcream = false
        }
    }

    fun Extrachocolate(androidView: View)
    {
        if(ExtraChocolate.isChecked==true) {
            extrachocolate=true
        }
        else {
            extrachocolate=false
        }
    }

    fun composeEmail(subject: String, body:String) {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:") // only email apps should handle this
            putExtra(Intent.EXTRA_SUBJECT, subject)
            putExtra(Intent.EXTRA_TEXT, body)
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

}