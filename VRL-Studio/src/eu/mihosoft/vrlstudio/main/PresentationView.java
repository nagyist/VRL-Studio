/* 
 * PresentationView.java
 * 
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2009–2015 Steinbeis Forschungszentrum (STZ Ölbronn),
 * Copyright (c) 2007–2017 by Michael Hoffer
 * 
 * This file is part of VRL-Studio.
 *
 * VRL-Studio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License version 3
 * as published by the Free Software Foundation.
 * 
 * see: http://opensource.org/licenses/LGPL-3.0
 *      file://path/to/VRL/src/eu/mihosoft/vrl/resources/license/lgplv3.txt
 *
 * VRL-Studio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * This version of VRL-Studio includes copyright notice and attribution requirements.
 * According to the LGPL this information must be displayed even if you modify
 * the source code of VRL-Studio. Neither the VRL Canvas attribution icon nor any
 * copyright statement/attribution may be removed.
 *
 * Attribution Requirements:
 *
 * If you create derived work you must do three things regarding copyright
 * notice and author attribution.
 *
 * First, the following text must be displayed on the Canvas:
 * "based on VRL source code". In this case the VRL canvas icon must be removed.
 * 
 * Second, keep the links to "About VRL-Studio" and "About VRL". The
 * copyright notice must remain.
 *
 * Third, add an additional notice, stating that you modified VRL. In addition
 * you must cite the publications listed below. A suitable notice might read
 * "VRL source code modified by YourName 2012".
 * 
 * Note, that these requirements are in full accordance with the LGPL v3
 * (see 7. Additional Terms, b).
 *
 * Publications:
 *
 * M. Hoffer, C. Poliwoda, & G. Wittum. (2013). Visual reflection library:
 * a framework for declarative GUI programming on the Java platform.
 * Computing and Visualization in Science, 2013, 16(4),
 * 181–192. http://doi.org/10.1007/s00791-014-0230-y
 */

package eu.mihosoft.vrlstudio.main;

import eu.mihosoft.vrl.animation.Animation;
import eu.mihosoft.vrl.animation.AnimationBase;
import eu.mihosoft.vrl.animation.FrameListener;
import eu.mihosoft.vrl.reflection.VisualCanvas;
import eu.mihosoft.vrl.visual.ImageUtils;
import eu.mihosoft.vrl.visual.PaintListener;
import eu.mihosoft.vrl.visual.VGraphicsUtil;
import eu.mihosoft.vrl.visual.VSwingUtil;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;

/**
 *
 * @author Michael Hoffer <info@michaelhoffer.de>
 */
public class PresentationView extends javax.swing.JFrame implements PaintListener {

    private VisualCanvas mainCanvas;
    private ViewPanel viewPanel = new ViewPanel();

    /** Creates new form PresentationView */
    public PresentationView() {
        initComponents();

        setLayout(new GridLayout());

        add(viewPanel);

        addComponentListener(new ComponentListener() {
            // This method is called after the component's size changes

            @Override
            public void componentResized(ComponentEvent evt) {
                viewPanel.fullRepaint();
//                repaint();
            }

            @Override
            public void componentMoved(ComponentEvent e) {
                viewPanel.fullRepaint();
//                repaint();
            }

            @Override
            public void componentShown(ComponentEvent e) {
                viewPanel.fullRepaint();
//                repaint();
            }

            @Override
            public void componentHidden(ComponentEvent e) {
                //
            }
        });
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setAlwaysOnTop(true);
        setResizable(false);
        setUndecorated(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 478, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 358, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
    //    /**
    //     * Progressive bilinear scaling: for any downscale size, scale
    //     * iteratively by halves using BILINEAR filtering until the proper 
    //     * size is reached.
    //     */
//    public static Image getOptimalScalingImage(Image inputImage,
//            int startSize, int endSize) {
//        int currentSize = startSize;
//        Image currentImage = inputImage;
//        int delta = currentSize - endSize;
//        int nextPow2 = currentSize >> 1;
//        while (currentSize > 1) {
//            if (delta <= nextPow2) {
//                if (currentSize != endSize) {
//                    BufferedImage tmpImage = new BufferedImage(endSize,
//                            endSize, BufferedImage.TYPE_INT_RGB);
//                    Graphics g = tmpImage.getGraphics();
//                    ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_INTERPOLATION,
//                            RenderingHints.VALUE_INTERPOLATION_BILINEAR);
//                    g.drawImage(currentImage, 0, 0, tmpImage.getWidth(),
//                            tmpImage.getHeight(), null);
//                    currentImage = tmpImage;
//                }
//                return currentImage;
//            } else {
//                BufferedImage tmpImage = new BufferedImage(currentSize >> 1,
//                        currentSize >> 1, BufferedImage.TYPE_INT_RGB);
//                Graphics g = tmpImage.getGraphics();
//                ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_INTERPOLATION,
//                        RenderingHints.VALUE_INTERPOLATION_BILINEAR);
//                g.drawImage(currentImage, 0, 0, tmpImage.getWidth(),
//                        tmpImage.getHeight(), null);
//                currentImage = tmpImage;
//                currentSize = currentImage.getWidth(null);
//                delta = currentSize - endSize;
//                nextPow2 = currentSize >> 1;
//            }
//        }
//        return currentImage;
//    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new PresentationView().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    /**
     * @return the mainCanvas
     */
    public VisualCanvas getMainCanvas() {
        return mainCanvas;
    }

    /**
     * @param mainCanvas the mainCanvas to set
     */
    public void setMainCanvas(VisualCanvas mainCanvas) {
        this.mainCanvas = mainCanvas;
        viewPanel.setMainCanvas(mainCanvas);
    }

    @Override
    public void paintEvent(Rectangle r, MouseEvent m) {
        viewPanel.paintEvent(r, m);
    }
}

//class MousePointer extends JPanel {
//
//    public MousePointer() {
//        setSize(20, 20);
//        setOpaque(true);
//    }
//
//    @Override
//    public void paint(Graphics g) {
//        g.setColor(Color.red);
//        g.fillOval(0, 0, getWidth(), getHeight());
//    }
//}
class ViewPanel extends JPanel implements PaintListener {

    private boolean fullImage = false;
    private VisualCanvas mainCanvas;
    private Point mousePointer;
    private Point previousMousePointer;
    private AffineTransform tr;
//    private MousePointer mousePtr = new MousePointer();

    public ViewPanel() {
        setOpaque(true);
//        add(mousePtr);
//        mousePtr.setVisible(true);
    }

    public ViewPanel(VisualCanvas mainCanvas) {
        setOpaque(true);
        this.mainCanvas = mainCanvas;
        setLayout(null);
//        add(mousePtr);
//        mousePtr.setVisible(true);
    }

    @Override
    public void paintComponent(Graphics g) {
        if (getMainCanvas() != null
                && getSize().width > 0 && getSize().height > 0) {
            Rectangle r = g.getClipBounds();

            Rectangle visibleRect = getMainCanvas().getVisibleRect();

            if (r == null || fullImage) {
                r = getMainCanvas().getVisibleRect();
            }

            Graphics2D g2 = (Graphics2D) g;

            tr = ImageUtils.createMatchingTransform(
                    new Dimension(visibleRect.width, visibleRect.height),
                    getSize());

            Point2D pos = new Point2D.Float(r.x, r.y);
            Point2D size = new Point2D.Float(r.width, r.height);

            tr.transform(pos, pos);
            tr.transform(size, size);

            if (fullImage) {
                BufferedImage img = getMainCanvas().snapShot();

                if (img == null) {
                    return;
                }

                AffineTransformOp op = new AffineTransformOp(tr,
                        AffineTransformOp.TYPE_BILINEAR);
                img = op.filter(img, null);

                g2.setClip(null);

                g2.drawImage(
                        img,
                        0, 0, null);
                fullImage = false;
            } else {
                g2.setClip((int) pos.getX(), (int) pos.getY(),
                        (int) size.getX(), (int) size.getY());

                BufferedImage img = getMainCanvas().snapShot();

                if (img == null) {
                    return;
                }

                try {
                    img = img.getSubimage(
                            r.x, r.y, r.width, r.height);

                    AffineTransformOp op = new AffineTransformOp(tr,
                            AffineTransformOp.TYPE_BILINEAR);

                    img = op.filter(img, null);

                    g2.drawImage(
                            img,
                            (int) pos.getX(), (int) pos.getY(), null);
                } catch (Exception ex) {
                    //
                }
            }
        }
    }

    @Override
    public void paintEvent(Rectangle r, MouseEvent m) {
        Graphics g = getGraphics();
        if (!fullImage) {
            g.setClip(r);
        }
        if (m != null) {
            setMousePointer(m.getPoint());
        }
        try {
            paintComponent(g);
        } catch (Exception ex) {
            //
        }
//        if (getMousePointer() != null) {
//
//            if (previousMousePointer == null) {
//                Rectangle clip = new Rectangle(getMousePointer().x - 10, getMousePointer().y - 10, 20, 20);
//                g.setClip(clip);
//            } else {
//                paintComponent(g);
//                int x = Math.min(previousMousePointer.x - 10, r.x);
//                int y = Math.min(previousMousePointer.y - 10, r.y);
//
//                int width = Math.abs(previousMousePointer.x - 10 + r.x) + r.width;
//                int height = Math.abs(previousMousePointer.y - 10 + r.y) + r.height;
//
//                Rectangle clip = new Rectangle(x, y, width, height);
//                g.setClip(clip);
//
//                g.drawRect(x, y, width, height);
//            }
//
//
//            g.setClip(new Rectangle(getMousePointer().x - 10, getMousePointer().y - 10, 20, 20));
//            g.setColor(Color.red);
//            g.fillOval(getMousePointer().x - 10, getMousePointer().y - 10, 20, 20);
//        }
    }

    /**
     * @return the mainCanvas
     */
    public VisualCanvas getMainCanvas() {
        return mainCanvas;
    }

    /**
     * @param mainCanvas the mainCanvas to set
     */
    public void setMainCanvas(VisualCanvas mainCanvas) {
        this.mainCanvas = mainCanvas;
    }

    void fullRepaint() {
        VSwingUtil.repaintRequestOnComponent(
                mainCanvas,
                0, 0,
                mainCanvas.getWidth(), mainCanvas.getHeight());
        fullImage = true;
    }

    /**
     * @return the mousePointer
     */
    public Point getMousePointer() {
        return mousePointer;
    }

    /**
     * @param mousePointer the mousePointer to set
     */
    public void setMousePointer(Point mousePointer) {
        Point scaledMousePointerPos = new Point();
        if (tr==null) {
            return;
        }
        tr.transform(mousePointer, scaledMousePointerPos);
        previousMousePointer = this.mousePointer;
        this.mousePointer = scaledMousePointerPos;
    }
}
