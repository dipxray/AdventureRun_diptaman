package tile;

import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.Graphics2D;
import main.GamePanel;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gp;
    Tile[] tile;

    int MapTileNum[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[50];
        getTileImage();
        MapTileNum = new int[gp.maxworldcol][gp.maxworldrow];
        loadMap();
    }

    public void getTileImage() {
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResource("/res/tiles/lava01.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResource("/res/tiles/temple01.png"));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResource("/res/tiles/skull01.png"));

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResource("/res/tiles/castle.png"));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResource("/res/tiles/road.png"));

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResource("/res/tiles/flametree01.png"));

            tile[6] = new Tile();
            tile[6].image = ImageIO.read(getClass().getResource("/res/tiles/coconut01.png"));

            tile[7] = new Tile();
            tile[7].image = ImageIO.read(getClass().getResource("/res/tiles/tile_road_1772650324566.png"));

            tile[8] = new Tile();
            tile[8].image = ImageIO.read(getClass().getResource("/res/tiles/bossroad.png"));

            tile[9] = new Tile();
            tile[9].image = ImageIO.read(getClass().getResource("/res/tiles/house.png"));

            tile[10] = new Tile();
            tile[10].image = ImageIO.read(getClass().getResource("/res/tiles/cottage01.png"));

            tile[11] = new Tile();
            tile[11].image = ImageIO.read(getClass().getResource("/res/tiles/cherry01.png"));

            tile[12] = new Tile();
            tile[12].image = ImageIO.read(getClass().getResource("/res/tiles/beach.png"));

            tile[13] = new Tile();
            tile[13].image = ImageIO.read(getClass().getResource("/res/tiles/grass01.png"));

            tile[14] = new Tile();
            tile[14].image = ImageIO.read(getClass().getResource("/res/tiles/jungle.png"));

            tile[15] = new Tile();
            tile[15].image = ImageIO.read(getClass().getResource("/res/tiles/flowerbed01.png"));

            tile[16] = new Tile();
            tile[16].image = ImageIO.read(getClass().getResource("/res/tiles/water01.png"));

            tile[17] = new Tile();
            tile[17].image = ImageIO.read(getClass().getResource("/res/tiles/apple.png"));

            tile[18] = new Tile();
            tile[18].image = ImageIO.read(getClass().getResource("/res/tiles/flower01.png"));

            tile[19] = new Tile();
            tile[19].image = ImageIO.read(getClass().getResource("/res/tiles/pine01.png"));

            tile[20] = new Tile();
            tile[20].image = ImageIO.read(getClass().getResource("/res/tiles/waves01.png"));

            tile[21] = new Tile();
            tile[21].image = ImageIO.read(getClass().getResource("/res/tiles/royalroad01.png"));

            tile[22] = new Tile();
            tile[22].image = ImageIO.read(getClass().getResource("/res/tiles/bcastle.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap() {

        try {

            InputStream is = getClass().getResourceAsStream("/res/mymap.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int row = 0;

            while (row < gp.maxworldrow) {

                String line = br.readLine();
                String numbers[] = line.split(" ");
                int i = 0;

                for (int col = 0; col < gp.maxworldcol && i < numbers.length; col++) {
                    if (numbers[i].trim().isEmpty()) {
                        i++;
                        col--;
                        continue;
                    }

                    int num = Integer.parseInt(numbers[i]);
                    MapTileNum[col][row] = num;
                    i++;
                }

                row++;
            }

            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        int worldcol = 0;
        int worldrow = 0;

        while (worldcol < gp.maxworldcol && worldrow < gp.maxworldrow) {
            int tileNum = MapTileNum[worldcol][worldrow];
            int worldx = worldcol * gp.tileSize;
            int worldy = worldrow * gp.tileSize;
            int screenx = worldx - gp.player.worldx + gp.player.screenx;
            int screeny = worldy - gp.player.worldy + gp.player.screeny;

            // RIGHT BOUNDARY OR LEFT BOUNDARY
            if (gp.player.screenx > gp.player.worldx) {
                screenx = worldx;
            }
            if (gp.screenWidth - gp.player.screenx > gp.worldwidth - gp.player.worldx) {
                screenx = gp.screenWidth - (gp.worldwidth - worldx);
            }

            // TOP BOUNDARY OR BOTTOM BOUNDARY
            if (gp.player.screeny > gp.player.worldy) {
                screeny = worldy;
            }
            if (gp.screenHeight - gp.player.screeny > gp.worldheight - gp.player.worldy) {
                screeny = gp.screenHeight - (gp.worldheight - worldy);
            }

            if (tile[tileNum] != null && tile[tileNum].image != null) {
                g2.drawImage(tile[tileNum].image, screenx, screeny, gp.tileSize, gp.tileSize, null);
            }
            worldcol++;

            if (worldcol == gp.maxworldcol) {
                worldcol = 0;
                worldrow++;
            }
        }

    }
}
