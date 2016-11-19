package xyz.morecraft.dev.xross.gofr.io;

import xyz.morecraft.dev.xross.gofr.engine.CellState;
import xyz.morecraft.dev.xross.gofr.engine.WorldType;
import xyz.morecraft.dev.xross.gofr.world.World;

import java.io.*;

public class IO {

    public static void saveFileAsTXT(String filename, World w, int step) {
        try {
            File file = new File(filename);

            if (!file.exists()) {
                if (file.createNewFile()) {
                    throw new IOException("File could not be created");
                }
            } else {
                System.out.println("File [" + filename + "] overwritten!");
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write("# SIZE: " + w.getWidth() + " " + w.getHeight());
            bw.newLine();
            bw.write("# CYCLE: " + step);
            bw.newLine();
            bw.write("# TYPE: " + w.getWorldType());
            bw.newLine();

            bw.write(w.toString());
//            for (int i = 0; i < w.getHeight(); i++) {
//                for (int j = 0; j < w.getWidth(); j++) {
//                    bw.write(w.getCell(i, j).toString());
//                }
//                bw.newLine();
//            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static World readFileFromTXT(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            World w = null;
            int cycle = -1;
            int row = 0;

            while ((line = br.readLine()) != null) {
                if (line.charAt(0) == '#') {
                    if (line.contains("SIZE")) {
                        String[] tab = line.substring(line.indexOf(":") + 1).trim().split("\\s+");
                        w = new World(Integer.parseInt(tab[0]), Integer.parseInt(tab[1]));
                    } else if (line.contains("TYPE")) {
                        if (w != null) {
                            w.setWorldType(WorldType.valueOf(line.substring(line.indexOf(":") + 1).trim()));
                        }
                    } else if (line.contains("CYCLE")) {
                        cycle = Integer.parseInt(line.substring(line.indexOf(":") + 1).trim());
                    }
                } else if (w != null) {
                    if (row > w.getHeight()) {
                        throw new IOException("Too many lines");
                    }
                    for (int i = 0; i < line.length(); i++) {
                        if (i > w.getWidth()) {
                            throw new IOException("Too many cells in line");
                        }
                        w.setCell(row, i, CellState.of(line.charAt(i)));
                    }
                    row++;
                }

            }
            br.close();
            if (w != null) {
                System.out.printf("File [%s] loaded; World %dx%d with state after %d cycles\n", filename, w.getWidth(), w.getHeight(), cycle);
            }
            return w;
        } catch (FileNotFoundException e) {
            System.err.printf("File [%s] not found\n", filename);
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
