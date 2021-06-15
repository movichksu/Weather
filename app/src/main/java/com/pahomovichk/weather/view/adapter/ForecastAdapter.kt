import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pahomovichk.weather.Constants
import com.pahomovichk.weather.R
import com.pahomovichk.weather.model.data.Forecast
import com.pahomovichk.weather.view.adapter.WeatherIcon
import java.lang.Exception


class ForecastAdapter internal constructor(
    private var forecast: List<Forecast>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object{
        val HAT_TYPE = 0
        val WEATHER_TYPE = 1
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View
        when (viewType) {
            HAT_TYPE -> {
                view =
                    LayoutInflater.from(viewGroup.context)
                        .inflate(com.pahomovichk.weather.R.layout.weather_hat_item, viewGroup, false)
                return HatViewHolder(view)
            }
            WEATHER_TYPE -> {
                view =
                    LayoutInflater.from(viewGroup.context)
                        .inflate(com.pahomovichk.weather.R.layout.weather_item, viewGroup, false)
                return WeatherViewHolder(view)
            }
            else -> throw Exception("No such viewType")
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (forecast[position].time.contains("00:00")) {
            return HAT_TYPE
        } else {
            return WEATHER_TYPE
        }
    }

    override fun getItemCount() = forecast.size

    fun setData(data: List<Forecast>) {
        this.forecast = data
        notifyDataSetChanged()
    }

    inner class HatViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val container: View = view.findViewById(com.pahomovichk.weather.R.id.hat_weather_container)
        val hat: TextView = view.findViewById(com.pahomovichk.weather.R.id.hat)
        val ico: ImageView = view.findViewById(com.pahomovichk.weather.R.id.weather_ic)
        val description: TextView = view.findViewById(com.pahomovichk.weather.R.id.description)
        val time: TextView = view.findViewById(com.pahomovichk.weather.R.id.time)
        val temperature: TextView = view.findViewById(com.pahomovichk.weather.R.id.temperature)
    }

    inner class WeatherViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val container: View = view.findViewById(com.pahomovichk.weather.R.id.weather_container)
        val ico: ImageView = view.findViewById(com.pahomovichk.weather.R.id.weather_ic)
        val description: TextView = view.findViewById(com.pahomovichk.weather.R.id.description)
        val time: TextView = view.findViewById(com.pahomovichk.weather.R.id.time)
        val temperature: TextView = view.findViewById(com.pahomovichk.weather.R.id.temperature)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val weather = forecast[position]
        when(holder){
            is HatViewHolder -> {
                holder.hat.text = "WEAK DAY"
                holder.ico.setImageResource(WeatherIcon.defineIcon(weather.ico).icon)
                holder.description.text = weather.main
                holder.time.text = weather.time
                holder.temperature.text = "${Math.round(weather.temp * 10) / 10}${Constants.CELSIUS}"
                Log.d("ICON", "${weather.ico}")
            }
            is WeatherViewHolder -> {
                holder.ico.setImageResource(WeatherIcon.defineIcon(weather.ico).icon)
                holder.description.text = weather.main
                holder.time.text = weather.time
                holder.temperature.text = "${Math.round(weather.temp * 10) / 10}${Constants.CELSIUS}"
            }
        }
    }

}