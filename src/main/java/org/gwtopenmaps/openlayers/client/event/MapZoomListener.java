package org.gwtopenmaps.openlayers.client.event;

import org.gwtopenmaps.openlayers.client.util.JSObject;

/**
 * Needs to be implemented to listen to map zoom events.
 *
 * @author Curtis Jensen
 *
 */
public interface MapZoomListener extends EventListener {

	class MapZoomEvent extends MapEvent {
		public MapZoomEvent(JSObject eventObject) {
			super(eventObject);
		}
	}

	void onMapZoom(MapZoomEvent eventObject);

}
