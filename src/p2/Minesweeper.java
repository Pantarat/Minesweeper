package p2;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Minesweeper extends JPanel implements ActionListener, KeyListener {
	static int clickcount = 0;
	Random rand = new Random();
	int surroundCheck;
	int width;
	int height;
	Tile[][] tile_array;
	static boolean gameEnded = true;
	private Timer endGame = new Timer(100, new EndGame());

	public Minesweeper(int width, int height, int difficulty) {
		reset();
		this.width = width;
		this.height = height;
		tile_array = new Tile[height][width];
		this.setLayout(new GridLayout(height, width));
		// generate mines
		int randomdiff;
		if (difficulty == 1) {
			randomdiff = 9;
		} else if (difficulty == 2) {
			randomdiff = 7;
		} else {
			randomdiff = 5;
		}
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				tile_array[i][j] = new Tile(rand.nextInt(randomdiff));
				tile_array[i][j].addActionListener(this);
				this.add(tile_array[i][j]);
			}
		}
		// Get surrounding mines
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				int surround = checkSurround(tile_array[i][j], i, j);
				tile_array[i][j].changeSurround(surround);
			}
		}
		this.addKeyListener(this);
		this.setFocusable(true);
		endGame.start();
	}

	public int checkSurround(Tile t, int i, int j) {
		surroundCheck = 0;
		//check edge cases
		if (i > 0 && i < height - 1 && j > 0 && j < width - 1) {
			for (int xCk = i - 1; xCk < (i + 2); xCk++) {
				for (int yCk = j - 1; yCk < (j + 2); yCk++) {
					if (tile_array[xCk][yCk].containsMine) {
						surroundCheck++;
					}
				}
			}
		} else if (i == 0 && j > 0 && j < width - 1) {
			for (int xCk = i; xCk < (i + 2); xCk++) {
				for (int yCk = j - 1; yCk < (j + 2); yCk++) {
					if (tile_array[xCk][yCk].containsMine) {
						surroundCheck++;
					}
				}
			}
		} else if (i == 0 && j == 0) {
			for (int xCk = i; xCk < (i + 2); xCk++) {
				for (int yCk = j; yCk < (j + 2); yCk++) {
					if (tile_array[xCk][yCk].containsMine) {
						surroundCheck++;
					}
				}
			}
		} else if (i == 0 && j == width - 1) {
			for (int xCk = i; xCk < (i + 2); xCk++) {
				for (int yCk = j - 1; yCk < (j + 1); yCk++) {
					if (tile_array[xCk][yCk].containsMine) {
						surroundCheck++;
					}
				}
			}
		} else if (i == height - 1 && j > 0 && j < width - 1) {
			for (int xCk = i - 1; xCk < (i + 1); xCk++) {
				for (int yCk = j - 1; yCk < (j + 2); yCk++) {
					if (tile_array[xCk][yCk].containsMine) {
						surroundCheck++;
					}
				}
			}
		} else if (i == height - 1 && j == 0) {
			for (int xCk = i - 1; xCk < (i + 1); xCk++) {
				for (int yCk = j; yCk < (j + 2); yCk++) {
					if (tile_array[xCk][yCk].containsMine) {
						surroundCheck++;
					}
				}
			}
		} else if (i == height - 1 && j == width - 1) {
			for (int xCk = i - 1; xCk < (i + 1); xCk++) {
				for (int yCk = j - 1; yCk < (j + 1); yCk++) {
					if (tile_array[xCk][yCk].containsMine) {
						surroundCheck++;
					}
				}
			}
		} else if (i > 0 && i < height - 1 && j == 0) {
			for (int xCk = i - 1; xCk < (i + 2); xCk++) {
				for (int yCk = j; yCk < (j + 2); yCk++) {
					if (tile_array[xCk][yCk].containsMine) {
						surroundCheck++;
					}
				}
			}
		} else if (i > 0 && i < height - 1 && j == width - 1) {
			for (int xCk = i - 1; xCk < (i + 2); xCk++) {
				for (int yCk = j - 1; yCk < (j + 1); yCk++) {
					if (tile_array[xCk][yCk].containsMine) {
						surroundCheck++;
					}
				}
			}
		}
		return surroundCheck;
	}

	public void clearSurroundIfZero(Tile t, int i, int j, int numSurround) {
		if (numSurround == 0) {
			//check edge cases
			if (i > 0 && i < height - 1 && j > 0 && j < width - 1) {
				for (int xCk = i - 1; xCk < (i + 2); xCk++) {
					for (int yCk = j - 1; yCk < (j + 2); yCk++) {
						tile_array[xCk][yCk].actionInt.actionPerformed(null);
					}
				}
			} else if (i == 0 && j > 0 && j < width - 1) {
				for (int xCk = i; xCk < (i + 2); xCk++) {
					for (int yCk = j - 1; yCk < (j + 2); yCk++) {
						tile_array[xCk][yCk].actionInt.actionPerformed(null);
					}
				}
			} else if (i == 0 && j == 0) {
				for (int xCk = i; xCk < (i + 2); xCk++) {
					for (int yCk = j; yCk < (j + 2); yCk++) {
						tile_array[xCk][yCk].actionInt.actionPerformed(null);
					}
				}
			} else if (i == 0 && j == width - 1) {
				for (int xCk = i; xCk < (i + 2); xCk++) {
					for (int yCk = j - 1; yCk < (j + 1); yCk++) {
						tile_array[xCk][yCk].actionInt.actionPerformed(null);
					}
				}
			} else if (i == height - 1 && j > 0 && j < width - 1) {
				for (int xCk = i - 1; xCk < (i + 1); xCk++) {
					for (int yCk = j - 1; yCk < (j + 2); yCk++) {
						tile_array[xCk][yCk].actionInt.actionPerformed(null);
					}
				}
			} else if (i == height - 1 && j == 0) {
				for (int xCk = i - 1; xCk < (i + 1); xCk++) {
					for (int yCk = j; yCk < (j + 2); yCk++) {
						tile_array[xCk][yCk].actionInt.actionPerformed(null);
					}
				}
			} else if (i == height - 1 && j == width - 1) {
				for (int xCk = i - 1; xCk < (i + 1); xCk++) {
					for (int yCk = j - 1; yCk < (j + 1); yCk++) {
						tile_array[xCk][yCk].actionInt.actionPerformed(null);
					}
				}
			} else if (i > 0 && i < height - 1 && j == 0) {
				for (int xCk = i - 1; xCk < (i + 2); xCk++) {
					for (int yCk = j; yCk < (j + 2); yCk++) {
						tile_array[xCk][yCk].actionInt.actionPerformed(null);
					}
				}
			} else if (i > 0 && i < height - 1 && j == width - 1) {
				for (int xCk = i - 1; xCk < (i + 2); xCk++) {
					for (int yCk = j - 1; yCk < (j + 1); yCk++) {
						tile_array[xCk][yCk].actionInt.actionPerformed(null);
					}
				}
			}
		}
	}

	int getNumberOfMines() {
		int nMine = 0;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (tile_array[i][j].containsMine) {
					nMine++;
				}
			}
		}
		return nMine;
	}

	int getNumberOfOpenTiles() {
		int nTile = 0;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (tile_array[i][j].clicked) {
					nTile++;
				}
			}
		}
		return nTile;
	}

	public void actionPerformed(ActionEvent e) {
		clickcount++;
		for (int a = 0; a < 10; a++) {
			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++) {
					// check if the first click is a mine: delete that mine
					if (clickcount == 1 && e.getSource() == tile_array[i][j] && tile_array[i][j].containsMine) {
						tile_array[i][j].containsMine = false;
						tile_array[i][j].changeSurround(checkSurround(tile_array[i][j], i, j));
						if (tile_array[i][j].numSurround != 0) {
							tile_array[i][j].setText("" + tile_array[i][j].numSurround);
						}
					}
					// check and clear all surrounding tiles if there are no surrounding tiles
					if (tile_array[i][j] == e.getSource()) {
						clearSurroundIfZero(tile_array[i][j], i, j, checkSurround(tile_array[i][j], i, j));
					}
					if (tile_array[i][j].clicked) {
						clearSurroundIfZero(tile_array[i][j], i, j, checkSurround(tile_array[i][j], i, j));
					}
				}
			}
		}
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
		if (gameEnded) {
			if(e.getKeyCode() == KeyEvent.VK_ENTER) {// check for ENTER key to restart game
				GenerateMinesweeper.gameFrame.dispose();
				}
			else {
				System.exit(0);
			}
		}
	}

	private void exitProgram(String txt) {
		endGame.stop();
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				this.remove(tile_array[i][j]);
			}
		}
		revalidate();
		repaint();
		gameEnded = true;
		SelectionGUI.clock.stopClock();
		this.setLayout(new BorderLayout());
		this.add(new JLabel("Press ENTER to restart or press any other key to exit", JLabel.CENTER), BorderLayout.SOUTH);
		JLabel endText = new JLabel(txt, JLabel.CENTER);
		endText.setFont(new Font("Verdana", Font.BOLD, 30));
		this.add(endText, BorderLayout.CENTER);
		this.requestFocusInWindow();
	}

	class EndGame implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (Tile.mineExplode) {
				// defeat
				exitProgram("Defeat!");
			} else if (getNumberOfOpenTiles() == (width * height) - getNumberOfMines()) {
				// victory
				exitProgram("Congratulations!");
			}

		}
	}

	protected void paintComponent(Graphics g) {

		super.paintComponent(g);
		int x = this.getWidth();
		int y = this.getHeight();
		if (gameEnded) {
			if (Tile.mineExplode) {
				// defeat draw a kaboom
				int[] star1x = { x * 8 / 16, x * 9 / 16, x * 12 / 16, x * 11 / 16, x * 14 / 16, x * 11 / 16,
						x * 12 / 16, x * 9 / 16, x * 8 / 16, x * 7 / 16, x * 4 / 16, x * 5 / 16, x * 2 / 16, x * 5 / 16,
						x * 4 / 16, x * 7 / 16 };
				int[] star1y = { y * 2 / 16, y * 5 / 16, y * 4 / 16, y * 7 / 16, y * 8 / 16, y * 9 / 16, y * 12 / 16,
						y * 11 / 16, y * 14 / 16, y * 11 / 16, y * 12 / 16, y * 9 / 16, y * 8 / 16, y * 7 / 16,
						y * 4 / 16, y * 5 / 16 };
				g.setColor(Color.red);
				g.fillPolygon(star1x, star1y, star1x.length);
				int[] star2x = { x * 8 / 16, x * 9 / 16, x * 11 / 16, x * 10 / 16, x * 13 / 16, x * 10 / 16,
						x * 11 / 16, x * 9 / 16, x * 8 / 16, x * 7 / 16, x * 5 / 16, x * 6 / 16, x * 3 / 16, x * 6 / 16,
						x * 5 / 16, x * 7 / 16 };
				int[] star2y = { y * 3 / 16, y * 6 / 16, y * 5 / 16, y * 7 / 16, y * 8 / 16, y * 9 / 16, y * 11 / 16,
						y * 10 / 16, y * 13 / 16, y * 10 / 16, y * 11 / 16, y * 9 / 16, y * 8 / 16, y * 7 / 16,
						y * 5 / 16, y * 6 / 16 };
				g.setColor(Color.yellow);
				g.fillPolygon(star2x, star2y, star1x.length);

			} else {
				// win draw trophy
				g.setColor(new Color(212, 175, 55));
				g.fillRect(x * 8 / 16 - x / 32, y * 6 / 16, x / 16, y * 6 / 16);
				int[] baseX = { x * 6 / 16, x * 10 / 16, x * 11 / 16, x * 5 / 16 };
				int[] baseY = { y * 12 / 16, y * 12 / 16, y * 13 / 16, y * 13 / 16 };
				g.fillPolygon(baseX, baseY, 4);
				g.setColor(new Color(255, 223, 0));
				g.fillArc(x * 4 / 16, -y * 2 / 16, x * 8 / 16, y * 8 / 16, 180, 180);
				g.fillRect(x * 4 / 16, y * 1 / 16, x * 8 / 16, y * 2 / 16);
				Graphics2D g2 = (Graphics2D) g; // typecast(implicit casting) to use setStroke increase line size
				g2.setStroke(new BasicStroke(10));
				g2.drawArc(x * 7 / 16, -y / 16, x * 6 / 16, y * 6 / 16, 270, 100);
				g2.drawArc(x * 3 / 16, -y / 16, x * 6 / 16, y * 6 / 16, 170, 100);
				g2.drawLine((x * 3 / 16) + x / 64, y / 16 + y * 3 / 64, x * 4 / 16, y / 16 + y * 3 / 64);
				g2.drawLine(x * 12 / 16, y / 16 + y * 3 / 64, x * 12 / 16 + x * 3 / 64, y / 16 + y * 3 / 64);
			}
		}
	}
	private void reset() {
		gameEnded = false;
		Tile.mineExplode = false;
		clickcount = 0;
	}
}
