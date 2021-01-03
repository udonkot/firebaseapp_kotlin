package com.example.firebaseapp

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

//    private lateinit var database: DatabaseReference

    // スタイルとフォントファミリーの設定
    private var mTypeface = Typeface.create(Typeface.SANS_SERIF, Typeface.NORMAL)
    // データの個数
    private val chartDataCount = 20

//    val lineChart = findViewById<LineChart>(R.id.lineChart)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))



        // グラフの設定
//        setupLineChart()
        // データの設定
//        lineChart.data = lineData(chartDataCount, 100f)

        val lineChart = findViewById<LineChart>(R.id.lineChart)
/*
        var database = Firebase.database.reference
        println("database")
        println(database.child("20201228").)
        var dbList = database.ref.
        println("[[[ref]]]")
        println(dbList.get().result)

        val entries: ArrayList<Entry> = ArrayList()
        entries.add(Entry(1f, 59.5f))
        entries.add(Entry(2f, 60.4f))
        entries.add(Entry(3f, 62.3f))
        entries.add(Entry(4f, 61.5f))
        entries.add(Entry(5f, 64.5f))
        entries.add(Entry(6f, 67.2f))
        entries.add(Entry(7f, 66.2f))
        entries.add(Entry(8f, 65.7f))
        entries.add(Entry(9f, 65.1f))
        entries.add(Entry(10f, 64.3f))
        entries.add(Entry(11f, 63.1f))

        val dataset = LineDataSet(entries, "# of Calls")

        val data = LineData(dataset)
        dataset.setColors(*ColorTemplate.COLORFUL_COLORS) //

        lineChart.data = data
//        lineChart.animateY(5000)
        lineChart.setExtraOffsets(20F,20F,20F,20F)

*/

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->

            Snackbar.make(view, "moooove!", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }


    // LineChart用のデータ作成
    private fun lineData(count: Int, range: Float): LineData {

        val values = mutableListOf<Entry>()

        for (i in 0 until count) {
            // 値はランダムで表示させる
            val value = (Math.random() * (range)).toFloat()
            values.add(Entry(i.toFloat(), value))
        }

        // グラフのレイアウトの設定
        val yVals = LineDataSet(values, "テストデータ").apply {
            axisDependency =  YAxis.AxisDependency.LEFT
            color = Color.BLACK
            // タップ時のハイライトカラー
            highLightColor = Color.YELLOW
            setDrawCircles(true)
            setDrawCircleHole(true)
            // 点の値非表示
            setDrawValues(false)
            // 線の太さ
            lineWidth = 2f
        }
        val data = LineData(yVals)
        return data
    }



}