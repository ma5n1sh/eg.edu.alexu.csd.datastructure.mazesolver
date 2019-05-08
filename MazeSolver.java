import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MazeSolver implements IMazeSolver {
   private boolean isDeadEnd(boolean[][] visted,int[] coordinates,char[][] entry){
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
    private void stackFiller(Stack s,int[] start,boolean[][] visited,char[][]entry){
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

    private boolean recursiveFinder(char [][] entry,boolean[][] visited,int[] start,Stack output){
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
    private void pointQueuer(queuell s, pathPoint start,int r, int c){
        pathPoint p=new pathPoint();
        p.point=new int[]{r,c};
        p.path=start.path.copy();
        p.path.add(start.point);
        s.enqueue(p);
    }

    private void queuefiller(queuell s,char[][]entry,boolean[][]visited, pathPoint start){
        int r,c;
        r=start.point[0]+1;c=start.point[1];
        if(r<entry.length) {
            if(!visited[r][c]&&entry[r][c]!='#'){pointQueuer(s,start,r,c);}
        }
        r=start.point[0]-1;c=start.point[1];
        if(r>=0) {
            if(!visited[r][c]&&entry[r][c]!='#'){
                pointQueuer(s,start,r,c);
            }
        }
        r=start.point[0];c=start.point[1]-1;
        if(c>=0) {
            if(!visited[r][c]&&entry[r][c]!='#'){
                pointQueuer(s,start,r,c);
            }
        }
        r=start.point[0];c=start.point[1]+1;
        if(c<entry[r].length) {
            if(!visited[r][c]&&entry[r][c]!='#'){pointQueuer(s,start,r,c);}
        }


    }

    private void booleanFalser(boolean[][] visited){
        for (int i=0;i<visited.length;i++){
            for(int j=0;j<visited[0].length;j++){visited[i][j]=false;}
        }
    }

    private sLinkedList BFS(char[][] entry,boolean[][]visited,int[] start){
        queuell q=new queuell();
        pathPoint p=new pathPoint();
        p.point=start;
        q.enqueue(p);
        while(!q.isEmpty()){pathPoint temp=(pathPoint) q.dequeue();
            visited[temp.point[0]][temp.point[1]]=true;
            if(entry[temp.point[0]][temp.point[1]]=='E'){temp.path.add(temp.point);return temp.path;}
            else if(isDeadEnd(visited,temp.point,entry)){continue;}
            else{
                queuefiller(q,entry,visited,temp);
            }
        }
        return null;
    }

    public int[][] solveBFS(java.io.File maze)  {
        char[][] entry= new char[0][];
        try {
            entry = fileTo2darray(maze);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        boolean[][] visited=new boolean[entry.length][entry[0].length];
        booleanFalser(visited);
        int[] start=startFinder(entry);
        sLinkedList Final=BFS(entry,visited,start);
        if(Final==null){return null;}
        int[][]output=new int[Final.size()][2];
        for(int i=0;i<Final.size();i++){
            output[i]=(int[])Final.get(i);
        }
        return output;
    }

    public int[][] solveDFS(java.io.File maze)  {
        char[][] entry= new char[0][];
        try {
            entry = fileTo2darray(maze);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Stack output=new Stack();
        boolean[][]visited=new boolean[entry.length][entry[0].length];
        booleanFalser(visited);
        recursiveFinder(entry,visited,startFinder(entry),output);
        if(output.isEmpty()){return null;}
        int[][] out=new int[output.size()][2];
        int i=0;
        while(!output.isEmpty()){out[i]=(int[])output.pop();i++;}
        return out;
    }
}


