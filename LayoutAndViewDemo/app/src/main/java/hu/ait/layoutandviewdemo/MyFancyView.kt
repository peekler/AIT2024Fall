package hu.ait.layoutandviewdemo

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class MyFancyView(ctx: Context, attrs: AttributeSet) : View(ctx, attrs){

    var paintBg: Paint = Paint()
    var paintLine: Paint = Paint()

    init {
        paintBg.setColor(Color.GREEN)
        paintBg.style = Paint.Style.FILL

        paintLine.setColor(Color.BLACK)
        paintLine.style = Paint.Style.STROKE
        paintLine.strokeWidth = 5f
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawRect(0f,0f, width.toFloat(),
            height.toFloat(), paintBg)
        canvas.drawLine(0f,0f, width.toFloat(),
            height.toFloat(), paintLine)


    }

}