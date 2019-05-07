import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MazeSolver  {
    private boolean isDeadEnd(boolean[][] visted, int[] coordinates, char[][] entry){
        int r,c;
        if(entry[coordinates[0]][coordinates[1]]=='E'){return false;}
        r=coordinates[0]+1;c=coordinates[1];
        if(r<entry.length) {
            if(!visted[r][c]&&entry[r][c]!='#'){return false;}
        }
        r=coordinates[0]-1;c=coordinates[1];
        if(r>=0) {
            if(!visted[r][c]&&entry[r][c]!='#'){return false;}
        }
        r=coordinates[0];c=coordinates[1]+1;
        if(c<entry[r].length) {
            if(!visted[r][c]&&entry[r][c]!='#'){return false;}
        }
        r=coordinates[0];c=coordinates[1]-1;
        if(c>=0) {
            if(!visted[r][c]&&entry[r][c]!='#'){return false;}
        }
        return true;
    }
    private char[][] fileTo2darray(File maze) throws FileNotFoundException {
        Scanner sc = new Scanner(maze);
        String s=sc.nextLine();
        int j=Character.getNumericValue(s.charAt(0));
        int c=Character.getNumericValue(s.charAt(2));
        String[] ss=new String[j];
        int i=0;
        while (i<j&&(sc.hasNextLine())){
            ss[i]=sc.nextLine();
            i++;
        }
        char [][] output=new char[j][c];
        for(i=0;i<ss.length;i++){output[i]=ss[i].toCharArray();}
        return output;
    }
    private void stackFiller(Stack s, int[] start, boolean[][] visited, char[][]entry){
        int r,c;
        r=start[0]-1;c=start[1];
        if(r>=0) {
            if(!visited[r][c]&&entry[r][c]!='#'){s.push(new int[]{r,c});}
        }
        r=start[0];c=start[1]-1;
        if(c>=0) {
            if(!visited[r][c]&&entry[r][c]!='#'){s.push(new int[]{r,c});}
        }
        r=start[0];c=start[1]+1;
        if(c<entry[r].length) {
            if(!visited[r][c]&&entry[r][c]!='#'){s.push(new int[]{r,c});}
        }

        r=start[0]+1;c=start[1];
        if(r<entry.length) {
            if(!visited[r][c]&&entry[r][c]!='#'){s.push(new int[]{r, c});}
        }

    }

    private boolean recursiveFinder(char [][] entry, boolean[][] visited, int[] start, Stack output){
        Stack s=new Stack();
        if(isDeadEnd(visited,start,entry)){visited[start[0]][start[1]]=true;return false;}
        else if(entry[start[0]][start[1]]=='E'){output.push(start);visited[start[0]][start[1]]=true;return true;}
        else{
            stackFiller(s,start,visited,entry);
            visited[start[0]][start[1]]=true;
            while(!s.isEmpty()){
                if(recursiveFinder(entry,visited,(int[])s.pop(),output)){
                    output.push(start);return true;
                }
            }
        }
        return false;
    }
    private int[]startFinder(char[][]entry){
        int[]start=new int[2];
        for(int i=0;i<entry.length;i++){
            for(int j=0;j<entry[0].length;j++){
                if(entry[i][j]=='S'){start[0]=i;start[1]=j;break;}
            }
        }
        return start;
    }

    public int[][] solveDFS(File maze) throws FileNotFoundException {
        char[][] entry=fileTo2darray(maze);
        Stack output=new Stack();
        boolean[][]visited=new boolean[entry.length][entry[0].length];
        for (int i=0;i<visited.length;i++){
            for(int j=0;j<visited[0].length;j++){visited[i][j]=false;}
        }
        recursiveFinder(entry,visited,startFinder(entry),output);
        if(output.isEmpty()){return null;}
        int[][] out=new int[output.size()][2];
        int i=0;
        while(!output.isEmpty()){out[i]=(int[])output.pop();i++;}
        return out;
    }
}


