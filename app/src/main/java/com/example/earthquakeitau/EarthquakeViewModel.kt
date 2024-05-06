import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.earthquakeitau.EarthquakeService
import com.example.earthquakeitau.model.EarthquakeResponse
import com.example.earthquakeitau.model.Feature
import kotlinx.coroutines.*

class EarthquakeViewModel : ViewModel() {
    val earthquakes = MutableLiveData<List<Feature>>()

    fun loadEarthquakes(starttime: String, endtime: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val earthquakeService = EarthquakeService.instance
            val earthquakeResponse = earthquakeService.getEarthquakes("geojson", starttime, endtime)
            earthquakes.postValue(earthquakeResponse.features)
        }
    }
}