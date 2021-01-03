package com.example.firebaseapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private lateinit var postReference: DatabaseReference

    override fun onStart() {
        super.onStart()
        println("start onStart")
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // グラフの設定
//        setupLineChart()
        // データの設定
//        lineChart.data = lineData(chartDataCount, 100f)

/*
        var database = Firebase.database.reference
        println("database")
        println(database.child("20201228").)
        var dbList = database.ref.
        println("[[[ref]]]")
        println(dbList.get().result)
*/
/*
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
*/
        //　オブジェクト取得
        val lineChart = view.findViewById<LineChart>(R.id.lineChart)

        // グラフデータ登録リスト
        val entries: ArrayList<Entry> = ArrayList()

        // DB初期化
        postReference = Firebase.database.reference

        // リスナー定義
        var postListener = object : ValueEventListener {
            // データ変更時
            override fun onDataChange(snapshot: DataSnapshot) {
                println("start onDataChange")

                //
                var  f = 1f
                for (snapshotChild in snapshot.children) {
                    for (postSnapshot in snapshotChild.children) {
                        var post = postSnapshot.getValue<WeightData>()
                        if (post != null) {
                            println("x:" + f + " y:" + post.weight)
                            entries.add(Entry(f, post.weight))
                            f++
                        }
                    }

                }

            }

            override fun onCancelled(error: DatabaseError) {
                println("start onCancelled")
                TODO("Not yet implemented")
            }

        }
        postReference.addValueEventListener(postListener)


        println("size:" + entries.size)

        val dataset = LineDataSet(entries, "# of Calls")
        val data = LineData(dataset)
//        dataset.setColors(*ColorTemplate.COLORFUL_COLORS) //
        lineChart.data = data
        lineChart.setExtraOffsets(20F,20F,20F,20F)


        view.findViewById<Button>(R.id.button_first).setOnClickListener {

//            var ref = FirebaseDatabase.getInstance().reference
//            println("instance ref child")
//            println(ref.child("child"))

            // 年月日、時分秒取得
            var currentTime = LocalDateTime.now()
            var date  = currentTime.format(DateTimeFormatter.ofPattern("yyyyMMdd"))
            var time =  currentTime.format(DateTimeFormatter.ofPattern("HHmmss"))

            //

            // RealDatabase connect
            var database = Firebase.database.reference

            // 入力値取得
            var weight = view.findViewById<TextView>(R.id.inputWeight).text
            println("weight: " + weight)
            var weightData = WeightData(weight.toString().toFloat(), time.toLong() )


            // 登録
            var dateKey = database.child(date).push().key
            if (dateKey != null) {

//                var data = Weight(weight).toMap()
                //　更新
                var childUpdates = hashMapOf<String, Any?>(
                    "/$date/$time/" to weightData
                )
                database.updateChildren(childUpdates)

                println("update data")
            } else {
                // 新規登録
                println("new data")
            }


//            println("child get")
//            println(database.ref("20201223").get())


//            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)



            println("show secondFragment");

        }
    }
}