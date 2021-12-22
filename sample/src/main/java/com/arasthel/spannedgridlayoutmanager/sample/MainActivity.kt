package com.arasthel.spannedgridlayoutmanager.sample

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import com.arasthel.spannedgridlayoutmanager.SpanSize
import com.arasthel.spannedgridlayoutmanager.SpannedGridLayoutManager
import com.arasthel.spannedgridlayoutmanager.SpannedGridLayoutManager.Orientation.VERTICAL

/**
 * Created by Jorge Martín on 24/5/17.
 */
class MainActivity : android.support.v7.app.AppCompatActivity() {

    val recyclerview: RecyclerView by lazy { findViewById<RecyclerView>(R.id.recyclerView) }

    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val spannedGridLayoutManager = SpannedGridLayoutManager(orientation = VERTICAL, spans = 4)
//        val spannedGridLayoutManager = SpannedGridLayoutManager(orientation = VERTICAL, spans = 3)
        spannedGridLayoutManager.itemOrderIsStable = true

        recyclerview.layoutManager = spannedGridLayoutManager

        recyclerview.addItemDecoration(SpaceItemDecorator(left = 10, top = 10, right = 10, bottom = 10))

        val adapter = GridItemAdapter(200)

        if (savedInstanceState != null && savedInstanceState.containsKey("clicked")) {
            val clicked = savedInstanceState.getBooleanArray("clicked")!!
            adapter.clickedItems.clear()
            adapter.clickedItems.addAll(clicked.toList())
        }

        spannedGridLayoutManager.spanSizeLookup = SpannedGridLayoutManager.SpanSizeLookup { position ->
            if (adapter.clickedItems[position]) {
                SpanSize(2, 2)
            } else {
                SpanSize(1, 1)
            }
            /*val a = position % 11
            when (a) {
                0, 7 -> SpanSize(2, 2)
                6 -> SpanSize(1, 2)
                else -> SpanSize(1, 1)
            }*/

            /*val type = position % 11
//            val type = Random.nextInt(0,2)
            when (type) {
                0, 2, 3, 4, 5, 6 -> SpanSize(3, 4)
                1, 10, 8, 7 -> SpanSize(3, 2)
                else -> SpanSize(3, 3)
            }*/
            /*when (position % 12) {
                0, 7 -> SpanSize(2, 2)
//                6 -> SpanSize(1, 2)
                else -> SpanSize(1, 1)
            }*/
        }

        recyclerview.adapter = adapter
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)

        outState?.putBooleanArray("clicked", (recyclerview.adapter as GridItemAdapter).clickedItems.toBooleanArray())

    }

}