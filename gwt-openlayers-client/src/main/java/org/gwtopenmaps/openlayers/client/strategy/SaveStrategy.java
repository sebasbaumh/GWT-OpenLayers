package org.gwtopenmaps.openlayers.client.strategy;

import org.gwtopenmaps.openlayers.client.util.JSObject;

/**
 * 
 * @author Maciej Jezierski - Pinocchio
 *
 */
public class SaveStrategy extends Strategy {
	
	public boolean activate() {
		return SaveStrategyImpl.activate(this.getJSObject());
	}
	
	public boolean deactivate() {
		return SaveStrategyImpl.deactivate(this.getJSObject());
	}
	
	/**
	 * Feature will be saved immediately after being added to the layer and with each modification or deletion
	 * @param enable 
	 */
	public void setAuto(boolean enable) {
		SaveStrategyImpl.setAuto(this.getJSObject(), enable);
	}
	
	/**
	 * Features will be saved on an interval provided by the value (in seconds)
	 * @param interval
	 */
	public void setAuto(int interval) {
		SaveStrategyImpl.setAuto(this.getJSObject(), interval);
	}
	
	/**
	 * Tell the layer protocol to commit unsaved features.  If the layer projection differs from the map projection, features will be transformed into the layer projection before being committed
	 */
	public void save() {
		SaveStrategyImpl.save(this.getJSObject());
	}
	
	/**
	 * Tell the layer protocol which features should be saved
	 * @param array array of VectorFeatures
	 */
	public void save(JSObject array) {
		SaveStrategyImpl.save(this.getJSObject(), array);
	}
	
	public SaveStrategy() {
		this(SaveStrategyImpl.create());
	}
	
	public SaveStrategy(JSObject strategy) {
		super(strategy);
	}
}