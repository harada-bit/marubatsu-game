import java.io.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Arrays;

public class MB3_3{
    // # 初期値
    static final int[] p_j = {0,2};
    static final int[] c_j = {0,1};
    static int[][] field = new int[3][3];
    static int[][] col_list = new int[3][3];
    static int[][] row_list = new int[3][3];
    static int[] col_lis = new int[3];
    static int[] crossL_List = new int[3];
    static int[] crossR_List = new int[3];

    static String a = "hello";

    public static void main(String[] args)throws IOException {
        // # 【関数】フィールド表示
        
        for (int i = 0; i < field.length; i++){
            for (int j = 0; j < field[i].length; j++){
                field[i][j] = 0;
            }
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Random r = new Random();

        field_data();

        
        while(true){

            // # ゲーム開始
            System.out.println("あなたの番です。：１があなたのコマです。");
            
            while(true){
                System.out.println("どこにしますか？行番と列番を指定して下さい");
                System.out.print("行番号入力：");
                String row_str = br.readLine();
                int row_int = Integer.parseInt(row_str);
                System.out.print("列番号入力：");
                String col_str = br.readLine();
                int col_int = Integer.parseInt(col_str);


                if (field[row_int-1][col_int-1] == 0){
                    field[row_int-1][col_int-1] = 1 ;
                    break;
                }
                else {
                    System.out.println("\nここには置けません。もう一度場所を指定して下さい。");
                }
            }
            
            boolean pack = false;
                    
            pack = pack_pro();
            if (pack == true){
                return;
            }
                
            System.out.println("CPUの番です：CPUは２の駒");
            // time.sleep(1)
            while (true){
                int row_c = r.nextInt(3);
                int col_c = r.nextInt(3);
                if (field[row_c][col_c] == 0){
                    field[row_c][col_c] = 2;
                    break;
                }       
            }
            pack = pack_pro();
            if (pack == true){
                return;
            }
        }
    }


    public static void field_data(){
        for (int[] lis:field){
            System.out.println(Arrays.toString(lis));
        }
        System.out.print("\n");
    }
        // # 【関数】勝敗判定
        
    public static boolean judge(){
        int pl = 0;
        int cp = 0;
        // # 左斜リスト

        for (int i:crossL_List){
            if(i == 1) pl+=1;
            else if(i == 2) cp+=1;
            if (pl == 3) {
                System.out.println("左斜あなたの勝ち");
                return true;
            }
            else if(cp == 3){
                System.out.println("あなたの負け");
                return true;
            }
        }

        pl = 0;
        cp = 0;
        
        // # 右斜リスト
        for (int i:crossR_List){
            if(i == 1) pl+=1;
            else if(i == 2) cp+=1;
            if (pl == 3) {
                System.out.println("右斜あなたの勝ち");
                return true;
            }
            else if(cp == 3){
                System.out.println("あなたの負け");
                return true;
            }
        }



        // # 列リスト

        for (int[] col_lis:col_list){
            pl = 0;
            cp = 0;

            for (int col_cell:col_lis){
                if(col_cell == 1)pl+=1;
                if(col_cell == 2)cp+=1;
            }
            if (pl==3){
                    System.out.println("列あなたの勝ち");
                    return true;
                }
            else if(cp==3){
                System.out.println("あなたの負け");
                return true;
            }
        }



        // # 行リスト

        for (int[] row_lis:row_list){
            pl = 0;
            cp = 0;
            for (int row_cell:row_lis){
                if(row_cell == 1)pl+=1;
                if(row_cell == 2)cp+=1;
            }
            if (pl==3){
                    System.out.println("行あなたの勝ち");
                    return true;
                }
            else if(cp==3){
                System.out.println("あなたの負け");
                return true;
            }
        }
    return false;
    }



        // # 【関数】行列斜めリストデータリスト構築
    public static void list_data(){
        int k = 2;
        int i = 0;
        int j = 0;
        for (int[] row_lis : field){
            for(int cell : row_lis){
                col_list[j][i] = cell;
                if (i == j){
                    crossL_List[j] = cell;
                }                           
                if (j == k){
                    crossR_List[j] = cell;
                    k -= 1;
                }
                j++;
            }
        j=0;
        i++; 
        }
    }
    // # 【関数】ループを抜ける
    public static boolean exit_judge(){
        int j = 0;
        int k = 0;
        for (int[] row_lis : field){
            for (int cell : row_lis){
                if(cell != 0) j++;
            }
            if (j == 3){
                k++;
            }
            j = 0;
        }
        if (k == 3){
            System.out.println("引き分けです");
            return true;
        }
    return false;
    }
    

    public static boolean pack_pro(){
        field_data();
        list_data();
        boolean kekka = false;
        boolean exit_kekka = false;
        kekka = judge();
        if (kekka == true){
            return true;
        }   
        exit_kekka = exit_judge();
        if (exit_kekka == true){
            return true;
        }
    return false;
    }
}