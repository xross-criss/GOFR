package xyz.morecraft.dev.xross.gofr.io;

import xyz.morecraft.dev.xross.gofr.engine.CellState;
import xyz.morecraft.dev.xross.gofr.world.World;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Output {

    private static char DeadChar = 'O';
    private static char AliveChar = 'X';
    static World w;

    public static World readFileFromTXT(String filename) {
        try ( BufferedReader br = new BufferedReader( new FileReader( filename ) ) ) {
            String line = br.readLine();
            int y = 0;

            if ( line == null ) {
                throw new IOException();
            }

            String[] a = line.split( "" );

            int width = Integer.valueOf( a[0] );
            int heigth = Integer.valueOf( a[1] );

            w = World.initializeWorld( width, heigth );

            while ( (line = br.readLine()) != null ) {
                if ( line.length() == 0 ) {
                    continue;
                }
                for ( int i = 0; i < line.length(); i++ ) {
                    if ( i > heigth ) {
                        throw new IOException( "za duze x" );
                    }

                    if ( line.charAt( i ) == Output.DeadChar ) {
                        //World.setCell( y, i, CellState.DEAD );
                        w = World.setDead(y, i);
                    } else if ( line.charAt( i ) == Output.AliveChar ) {
                        //World.setCell( y, i, CellState.ALIVE );
                        w = World.setAlive(y, i);
                    }

                }
                y++;
                if ( y > width ) {
                    throw new IOException( "za duze y" );
                }

            }
            br.close();

        } catch ( FileNotFoundException e ) {
            System.out.println( "Nie znaleziono pliku : " + filename );
            System.exit( 0 );
        } catch ( IOException e ) {
            e.printStackTrace();
        }
        return w;
    }
}
