package com.gpetuhov.android.sampleopenstreetmap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import com.pawegio.kandroid.defaultSharedPreferences
import kotlinx.android.synthetic.main.activity_main.*
import org.osmdroid.config.Configuration
import org.osmdroid.views.CustomZoomButtonsController
import org.osmdroid.util.GeoPoint

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Don't forget to check "dangerous" permissions first, before map is created
        // (not needed in this example)

        // Load/initialize the osmdroid configuration
        Configuration.getInstance().load(applicationContext, application.defaultSharedPreferences)
        // setting this before the layout is inflated is a good idea
        // it 'should' ensure that the map has a writable location for the map cache, even without permissions
        // if no tiles are displayed, you can try overriding the cache path using Configuration.getInstance().setCachePath
        // see also StorageUtils
        // note, the load method also sets the HTTP User Agent to your application's package name,
        // abusing osm's tile servers will get you banned based on this string

        // Inflate and create the map
        setContentView(R.layout.activity_main)

        mapView.setTileSource(TileSourceFactory.MAPNIK)

        // Init zoom controls
        mapView.zoomController?.setVisibility(CustomZoomButtonsController.Visibility.SHOW_AND_FADEOUT)

        // Ability to zoom with 2 fingers
        mapView.setMultiTouchControls(true)

        // Move map to default point and zoom
        val mapController = mapView.controller
        mapController?.setZoom(9.5)
        val startPoint = GeoPoint(48.8583, 2.2944)
        mapController?.setCenter(startPoint)
    }

    // Don't forget to call mapView.onResume() and onPause()
    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }
}
