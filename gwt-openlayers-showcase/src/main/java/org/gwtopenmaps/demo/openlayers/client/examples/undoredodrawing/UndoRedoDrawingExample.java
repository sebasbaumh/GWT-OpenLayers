package org.gwtopenmaps.demo.openlayers.client.examples.undoredodrawing;

import org.gwtopenmaps.demo.openlayers.client.InfoPanel;
import org.gwtopenmaps.demo.openlayers.client.basic.AbstractExample;
import org.gwtopenmaps.openlayers.client.LonLat;
import org.gwtopenmaps.openlayers.client.Map;
import org.gwtopenmaps.openlayers.client.MapOptions;
import org.gwtopenmaps.openlayers.client.MapWidget;
import org.gwtopenmaps.openlayers.client.control.DrawFeature;
import org.gwtopenmaps.openlayers.client.control.LayerSwitcher;
import org.gwtopenmaps.openlayers.client.control.OverviewMap;
import org.gwtopenmaps.openlayers.client.control.ScaleLine;
import org.gwtopenmaps.openlayers.client.handler.PathHandler;
import org.gwtopenmaps.openlayers.client.layer.TransitionEffect;
import org.gwtopenmaps.openlayers.client.layer.Vector;
import org.gwtopenmaps.openlayers.client.layer.WMS;
import org.gwtopenmaps.openlayers.client.layer.WMSOptions;
import org.gwtopenmaps.openlayers.client.layer.WMSParams;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;

public class UndoRedoDrawingExample extends AbstractExample {

    private DrawFeature drawLineFeatureControl = null;

    /**
     * Constructor.
     *
     * @param title The title of the example
     */
    public UndoRedoDrawingExample(String title) {
        super(title);
    }

    @Override
    public void buildPanel() {
        // create controls
        final HTML htmlInfo = new HTML("<p>This example shows how you can undo, and redo actions while drawing.</p><p>Draw some lines on the map, but <B>don't end the drawing by double clicking</B>. Then use the UNDO and REDO buttons.</p><p>These buttons simply call the undo() and redo() method on the DrawFeature control.");
        final InfoPanel info = new InfoPanel("Note that this example does not show how to undo/redo completed actions. For this another example is available.");

        // create some MapOptions
        MapOptions defaultMapOptions = new MapOptions();
        defaultMapOptions.setNumZoomLevels(16);

        // Create a MapWidget
        MapWidget mapWidget = new MapWidget("514px", "258px", defaultMapOptions);
        // Create a WMS layer as base layer
        WMSParams wmsParams = new WMSParams();
        wmsParams.setFormat("image/png");
        wmsParams.setLayers("basic");
        wmsParams.setStyles("");

        WMSOptions wmsLayerParams = new WMSOptions();
        wmsLayerParams.setUntiled();
        wmsLayerParams.setTransitionEffect(TransitionEffect.RESIZE);

        String wmsUrl = "http://vmap0.tiles.osgeo.org/wms/vmap0";
        WMS wmsLayer = new WMS("Basic WMS", wmsUrl, wmsParams, wmsLayerParams);

        // Add the WMS to the map
        Map map = mapWidget.getMap();
        map.addLayer(wmsLayer);

        // Create the Vector layer on which the user can draw new widgets
        final Vector vectorLayer = new Vector("Vector layer");
        map.addLayer(vectorLayer);

        // Create the drawline control
        drawLineFeatureControl = new DrawFeature(vectorLayer, new PathHandler());
        map.addControl(drawLineFeatureControl);
        drawLineFeatureControl.activate();

        final Button butUndo = new Button("UNDO");
        final Button butRedo = new Button("REDO");

        butUndo.addClickHandler(new ClickHandler()
        {
            public void onClick(ClickEvent event)
            {
                drawLineFeatureControl.undo(); //the actual undo action
            }
        });

        butRedo.addClickHandler(new ClickHandler()
        {

            public void onClick(ClickEvent event)
            {
                drawLineFeatureControl.redo(); //the actual redo action
            }
        });

        // Lets add some default controls to the map
        map.addControl(new LayerSwitcher()); // + sign in the upperright corner to display the layer switcher
        map.addControl(new OverviewMap()); // + sign in the lowerright to display the overviewmap
        map.addControl(new ScaleLine()); // Display the scaleline

        // Center and zoom to a location
        map.setCenter(new LonLat(0, 0), 5);

        contentPanel.add(htmlInfo);
        contentPanel.add(info);
        contentPanel.add(mapWidget);
        contentPanel.add(butUndo);
        contentPanel.add(butRedo);

        initWidget(contentPanel);

        mapWidget.getElement().getFirstChildElement().getStyle().setZIndex(0); // force the map to fall behind popups
    }

    @Override
    public String getSourceCodeURL() {
        return GWT.getModuleBaseURL() + "examples/undoredodrawing/"
                + "UndoRedoDrawingExample.txt";
    }
}