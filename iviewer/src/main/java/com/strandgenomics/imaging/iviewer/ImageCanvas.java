/**
 * openImaDis - Open Image Discovery: Image Life Cycle Management Software
 * Copyright (C) 2011-2016  Strand Life Sciences
 *   
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.strandgenomics.imaging.iviewer;

import java.awt.BasicStroke;
import java.awt.Image;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

import com.strandgenomics.imaging.iviewer.va.VAController;

import edu.umd.cs.piccolo.PCanvas;
import edu.umd.cs.piccolo.PLayer;
import edu.umd.cs.piccolo.nodes.PImage;
import edu.umd.cs.piccolo.util.PBounds;
import edu.umd.cs.piccolox.handles.PHandle;

public class ImageCanvas extends PCanvas {

	Image img;
	private ZoomHandler rubberBandHandler;
	private PBounds zoomBounds;
	private double zoomMultiplier;
	
	private boolean fitWidth = true;

	public ImageCanvas() {
		setSize(700, 700);

		rubberBandHandler = new ZoomHandler(getLayer(), this);

		removeInputEventListener(getPanEventHandler());
		addInputEventListener(rubberBandHandler);

		setVisible(true);
	}
	
	public ZoomHandler getRubberBandHandler() {
		return this.rubberBandHandler;
	}
	
	public void setFitWidth() {
		Point2D zoomCenter = getLayer().getGlobalFullBounds().getCenter2D();
		if (zoomCenter.getX() > 0) {
			getCamera().animateViewToCenterBounds(
					getLayer().getGlobalFullBounds(), true, 0);
			double multiplier = getCamera().getViewScale();
			setZoom(multiplier);
		}
		
		fitWidth = true;
	}
	
	public void toggleFitWidth() {
		setFitWidth();
	}
	
	public void addPLayer(PLayer layer) {
		getLayer().removeAllChildren();
		getLayer().addChild(layer);
		
		if(fitWidth) {
			setFitWidth();
		}
		else{
			PBounds zoomBounds = null;
			double currentScale = getCamera().getViewScale();
			double zoomLevel = getZoomMultiplier() / currentScale;

			if (rubberBandHandler.marquee == null) {
				zoomBounds = getCamera().getViewBounds();

			} else {
				zoomBounds = rubberBandHandler.marquee
						.getGlobalFullBounds();
			}

			getCamera().scaleView(zoomLevel);
			getCamera().animateViewToCenterBounds(zoomBounds, false, 0);
		}
	}
	
	public void addImage(Image img) {
		getLayer().removeAllChildren();

		this.img = img;
		PImage image = new PImage(img);	
		getLayer().addChild(image);
	}
	
	private void updateVAHandles(){
		final float handlesize = (float) (8 / zoomMultiplier);
		PHandle.DEFAULT_HANDLE_SHAPE = new Ellipse2D.Float(0f, 0f,
				handlesize, handlesize);

		VAController.DEFAULT_STROKE = new BasicStroke(
				handlesize / 8);
	}

	public void setZoom(double value) {
		if (rubberBandHandler.marquee == null) {
			zoomBounds = getCamera().getViewBounds();
		} else {
			zoomBounds = rubberBandHandler.marquee
					.getGlobalFullBounds();
		}
		
		this.setZoomMultiplier(value);

		double currentScale = getCamera().getViewScale();
		double zoomLevel = value / currentScale;
		
		getCamera().scaleView(zoomLevel);
		getCamera().animateViewToCenterBounds(zoomBounds, false, 0);
		
		fitWidth = false;
		
		updateVAHandles();
		
		repaint();
	}

	public void setZoomMultiplier(double zoomMultiplier) {
		this.zoomMultiplier = zoomMultiplier;
	}

	public double getZoomMultiplier() {
		return this.zoomMultiplier;
	}
}
