/**
 *
 *   Copyright 2013 sourceforge.
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */
package org.gwtopenmaps.openlayers.client.control;

import org.gwtopenmaps.openlayers.client.event.EventListener;
import org.gwtopenmaps.openlayers.client.event.EventObject;


/**
 * @author Frank Wynants
 *
 */
public interface TransformCompleteListener extends EventListener
{

    void onTransformComplete(TransformCompleteEvent eventObject);

    class TransformCompleteEvent extends TransformEvent
    {
        public TransformCompleteEvent(EventObject eventObject)
        {
            super(eventObject.getJSObject());
        }

        public String getFid()
        {
            return getJSObject().getProperty("feature").getPropertyAsString("fid");
        }
    }
}