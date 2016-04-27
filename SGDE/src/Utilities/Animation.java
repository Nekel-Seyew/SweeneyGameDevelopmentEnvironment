/*
Copyright (c) 2011-2016, Kyle D. Sweeney
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:
    * Redistributions of source code must retain the above copyright
      notice, this list of conditions and the following disclaimer.
    * Redistributions in binary form must reproduce the above copyright
      notice, this list of conditions and the following disclaimer in the
      documentation and/or other materials provided with the distribution.
    * Neither the name of the <organization> nor the
      names of its contributors may be used to endorse or promote products
      derived from this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE U
 */
package Utilities;

import java.awt.Color;
import java.awt.Rectangle;
import java.io.Serializable;

/**
 * This class allows for animations to be made using Image2D's.
 *
 * @author HAL-9000
 */
public class Animation implements Serializable{

    protected Image2D[] cells;
    protected int counter;
    protected int cellCreationCounter;
    protected int depth;
    protected Vector2 position;
    protected int rate;
    protected long time;
    protected boolean stripAnimation;

    public Animation(int numberOfCells, int depth, Vector2 pos, int wait) {
        cells = new Image2D[numberOfCells];
        counter = 0;
        cellCreationCounter = 0;
        this.depth = depth;
        position = pos;
        rate = wait;
        stripAnimation = false;
    }

    public Animation(String strip, Vector2 pos, int wait, int depth) {
        stripAnimation = true;
        cells = new Image2D[1];
        counter = 0;
        cellCreationCounter = 0;
        this.depth = depth;
        rate = wait;
        position = pos;
        cells[0] = new Image2D(strip);
    }

    /**
     * Will add a cell to the next open space. If no space is available, then
     * returns false
     *
     * @param fileName the string path of the sprite
     * @return true if the operation was successful, false if no space is
     * available in the array
     */
    public boolean addCell(String fileName) {
        if (cellCreationCounter >= cells.length) {
            return false;
        }
        cells[cellCreationCounter++] = new Image2D(fileName);
        return true;
    }

    public int getNumCells() {
        return cells.length;
    }

    public int getNumUsedCells() {
        int r = 0;
        for (Image2D d : cells) {
            if (d != null) {
                r++;
            }
        }
        return r;
    }

    public int getDepth() {
        return depth;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public boolean isStripAnimation() {
        return stripAnimation;
    }

    public Image2D getCurrentImage() {
        Image2D d;
        try {
            d = cells[counter];
        } catch (Exception e) {
            counter = 0;
            d = cells[counter];
        }
        return d;
    }

    public void Draw(ImageCollection b) {
        if (stripAnimation) {
            long t = System.currentTimeMillis();
            int height = cells[0].getHeight();
            int width = cells[0].getWidth();
            if (t - time >= rate) {
                if (counter >= width / height) {
                    counter = 0;
                }
                b.Draw(cells[0], position, new Rectangle(counter * height, 0, width, height), depth);
                counter++;
                time = t;
            } else {
                b.Draw(cells[0], position, new Rectangle(counter * height, 0, width, height), depth);
            }
        } else {
            if (cells.length == 1) {
                b.Draw(cells[0], position, depth);
                return;
            }
            long t = System.currentTimeMillis();
            try {
                if (t - time >= rate) {
                    if (counter >= cells.length) {
                        counter = 0;
                    }
                    b.Draw(cells[counter++], position, depth);
                    time = t;
                } else {
                    b.Draw(cells[counter], position, depth);
                }
            } catch (Exception e) {
                if (e instanceof ArrayIndexOutOfBoundsException) {
                    counter = 0;
                    time = t;
                    b.Draw(cells[counter], position, depth);
                }
            }
        }
    }

    public void Draw(ImageCollection b, float angle) {
        if (stripAnimation) {
            long t = System.currentTimeMillis();
            int height = cells[0].getHeight();
            int width = cells[0].getWidth();
            if (t - time >= rate) {
                if (counter >= width / height) {
                    counter = 0;
                }
                b.Draw(cells[0], position, angle, new Rectangle(counter * height, 0, width, height), depth);
                counter++;
                time = t;
            } else {
                b.Draw(cells[0], position, angle, new Rectangle(counter * height, 0, width, height), depth);
            }
        } else {
            if (cells.length == 1) {
                b.Draw(cells[0], position, angle, depth);
                return;
            }
            long t = System.currentTimeMillis();
            try {
                if (t - time >= rate) {
                    if (counter >= cells.length) {
                        counter = 0;
                    }
                    b.Draw(cells[counter++], position, angle, depth);
                    time = t;
                } else {
                    b.Draw(cells[counter], position, angle, depth);
                }
            } catch (Exception e) {
                if (e instanceof ArrayIndexOutOfBoundsException) {
                    counter = 0;
                    time = t;
                    b.Draw(cells[counter], position, angle, depth);
                }
            }
        }
    }

    public void Draw(ImageCollection b, float angle, Rectangle drawnArea) {
        if (stripAnimation) {
            long t = System.currentTimeMillis();
            int height = cells[0].getHeight();
            int width = cells[0].getWidth();
            if (t - time >= rate) {
                if (counter >= width / height) {
                    counter = 0;
                }
                b.Draw(cells[0], position, angle, new Rectangle(counter * height, 0, width, height), depth);
                counter++;
                time = t;
            } else {
                b.Draw(cells[0], position, angle, new Rectangle(counter * height, 0, width, height), depth);
            }
        } else {
            if (cells.length == 1) {
                b.Draw(cells[0], position, angle, drawnArea, depth);
                return;
            }
            long t = System.currentTimeMillis();
            try {
                if (t - time >= rate) {
                    if (counter >= cells.length) {
                        counter = 0;
                    }
                    b.Draw(cells[counter++], position, angle, drawnArea, depth);
                    time = t;
                } else {
                    b.Draw(cells[counter], position, angle, drawnArea, depth);
                }
            } catch (Exception e) {
                if (e instanceof ArrayIndexOutOfBoundsException) {
                    counter = 0;
                    time = t;
                    b.Draw(cells[counter], position, angle, drawnArea, depth);
                }
            }
        }
    }
    
    public void Draw(ImageCollection b, float angle, Vector2 pos, float scaleX, float scaleY, int depth, Rect drawnArea){
        this.position=new Vector2(pos);
        if (stripAnimation) {
            long t = System.currentTimeMillis();
            int height = cells[0].getHeight();
            int width = cells[0].getWidth();
            if (t - time >= rate) {
                if (counter >= width / height) {
                    counter = 0;
                }
                b.Draw(cells[0], position, angle, scaleX, scaleY, new Rectangle(counter * height, 0, width, height), depth);
                counter++;
                time = t;
            } else {
                b.Draw(cells[0], position, angle, scaleX, scaleY, new Rectangle(counter * height, 0, width, height), depth);
            }
        } else {
            if (cells.length == 1) {
                b.Draw(cells[0], position, angle, scaleX, scaleY, drawnArea, depth);
                return;
            }
            long t = System.currentTimeMillis();
            try {
                if (t - time >= rate) {
                    if (counter >= cells.length) {
                        counter = 0;
                    }
                    b.Draw(cells[counter++], position, angle, scaleX, scaleY, drawnArea, depth);
                    time = t;
                } else {
                    b.Draw(cells[counter], position, angle, scaleX, scaleY, drawnArea, depth);
                }
            } catch (Exception e) {
                if (e instanceof ArrayIndexOutOfBoundsException) {
                    counter = 0;
                    time = t;
                    b.Draw(cells[counter], position, angle, scaleX, scaleY, drawnArea, depth);
                }
            }
        }
    }

    public void Draw(ImageCollection b, float angle, Rectangle drawnArea, float scaleX, float scaleY) {
        if (stripAnimation) {
            long t = System.currentTimeMillis();
            int height = cells[0].getHeight();
            int width = cells[0].getWidth();
            if (t - time >= rate) {
                if (counter >= width / height) {
                    counter = 0;
                }
                b.Draw(cells[0], position, angle, scaleX, scaleY, new Rectangle(counter * height, 0, width, height), depth);
                counter++;
                time = t;
            } else {
                b.Draw(cells[0], position, angle, scaleX, scaleY, new Rectangle(counter * height, 0, width, height), depth);
            }
        } else {
            if (cells.length == 1) {
                b.Draw(cells[0], position, angle, scaleX, scaleY, drawnArea, depth);
                return;
            }
            long t = System.currentTimeMillis();
            try {
                if (t - time >= rate) {
                    if (counter >= cells.length) {
                        counter = 0;
                    }
                    b.Draw(cells[counter++], position, angle, scaleX, scaleY, drawnArea, depth);
                    time = t;
                } else {
                    b.Draw(cells[counter], position, angle, scaleX, scaleY, drawnArea, depth);
                }
            } catch (Exception e) {
                if (e instanceof ArrayIndexOutOfBoundsException) {
                    counter = 0;
                    time = t;
                    b.Draw(cells[counter], position, angle, scaleX, scaleY, drawnArea, depth);
                }
            }
        }
    }

    public void Draw(ImageCollection b, float angle, Rectangle drawnArea, float scaleX, float scaleY, Color tint) {
        if (stripAnimation) {
            long t = System.currentTimeMillis();
            int height = cells[0].getHeight();
            int width = cells[0].getWidth();
            if (t - time >= rate) {
                if (counter >= width / height) {
                    counter = 0;
                }
                b.Draw(cells[0], position, angle, tint, scaleX, scaleY, new Rectangle(counter * height, 0, width, height), depth);
                counter++;
                time = t;
            } else {
                b.Draw(cells[0], position, angle, tint, scaleX, scaleY, new Rectangle(counter * height, 0, width, height), depth);
            }
        } else {
            if (cells.length == 1) {
                b.Draw(cells[0], position, angle, tint, scaleX, scaleY, drawnArea, depth);
                return;
            }
            long t = System.currentTimeMillis();
            try {
                if (t - time >= rate) {
                    if (counter >= cells.length) {
                        counter = 0;
                    }
                    b.Draw(cells[counter++], position, angle, tint, scaleX, scaleY, drawnArea, depth);
                    time = t;
                } else {
                    b.Draw(cells[counter], position, angle, tint, scaleX, scaleY, drawnArea, depth);
                }
            } catch (Exception e) {
                if (e instanceof ArrayIndexOutOfBoundsException) {
                    counter = 0;
                    time = t;
                    b.Draw(cells[counter], position, angle, tint, scaleX, scaleY, drawnArea, depth);
                }
            }
        }
    }

    public double getWidth() {
        return getCurrentImage().getWidth();
    }

    public double getHeight() {
        return getCurrentImage().getHeight();
    }
}
