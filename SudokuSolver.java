import java.util.Scanner;

public class SudokuSolver
{
  public static void main(String[] args)
  {
    int[][] board = {{0, 0, 0, 0, 0, 0, 0, 0, 0},
                     {0, 0, 0, 0, 0, 0, 0, 0, 0},
                     {0, 0, 0, 0, 0, 0, 0, 0, 0},
                     {0, 0, 0, 0, 0, 0, 0, 0, 0},
                     {0, 0, 0, 0, 0, 0, 0, 0, 0},
                     {0, 0, 0, 0, 0, 0, 0, 0, 0},
                     {0, 0, 0, 0, 0, 0, 0, 0, 0},
                     {0, 0, 0, 0, 0, 0, 0, 0, 0},
                     {0, 0, 0, 0, 0, 0, 0, 0, 0}};
    int[][] userBoard = initBoard(board);
    System.out.println();
    System.out.println("Original Board:");
    System.out.println();
    printArray(userBoard);
    System.out.println();
    System.out.println("Solved Board:");
    System.out.println();
    solve(userBoard);
  }

  public static void printArray(int[][] board)
  {
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        if (j == board[i].length - 1) {
          System.out.print(board[i][j]);
        } else {
          if (j == 0) {
            System.out.print(" ");
          }
          System.out.print(board[i][j] + " | ");
        }
      }
      System.out.println();
      System.out.println("---+---+---+---+---+---+---+---+---");
    }
  }

  public static int[][] initBoard(int[][] board)
  {
    Scanner kb = new Scanner(System.in);
    int posNumber = 0;
    String strPosNumber = "";
    boolean validNumber;
    System.out.println("Enter each given number in the board row by row, from left to right. If blank, enter '0'.");
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        validNumber = false;
        while (!validNumber) {
          System.out.print("Number in position: ");
          strPosNumber = kb.nextLine();
          if (strPosNumber.compareTo("0") >= 0 && strPosNumber.compareTo("9") <= 0) {
            posNumber = Integer.parseInt(strPosNumber);
            if ((posNumber >= 1 && posNumber <= 9 && isValid(i, j, posNumber, board)) || (posNumber == 0)) {
              board[i][j] = posNumber;
              validNumber = true;
            } else {
              System.out.println("Invalid number. Enter again.");
            }
          } else {
            System.out.println("Invalid number. Enter again.");
          }
        }
      }
    }
    return board;
  }

  public static boolean posRowChecker(int row, int num, int[][] board)
  {
    boolean invalidRow = false;
    for (int i = 0; i < 9; i++) {
      if (board[row][i] == num) {
        invalidRow = true;
      }
    }
    return invalidRow;
  }

  public static boolean posColChecker(int col, int num, int[][] board)
  {
    boolean invalidCol = false;
    for (int i = 0; i < 9; i++) {
      if (board[i][col] == num) {
        invalidCol = true;
      }
    }
    return invalidCol;
  }

  public static boolean posBoxChecker(int row, int col, int num, int[][] board)
  {
    boolean invalidBox = false;
    int boxStartRow = row - (row % 3);
    int boxStartCol = col - (col % 3);
    for (int i = boxStartRow; i < boxStartRow + 3; i++) {
      for (int j = boxStartCol; j < boxStartCol + 3; j++) {
        if (board[i][j] == num) {
          invalidBox = true;
        }
      }
    }
    return invalidBox;
  }

  public static boolean isValid(int row, int col, int num, int[][] board)
  {
    return (!posRowChecker(row, num, board) && !posColChecker(col, num, board) && !posBoxChecker(row, col, num, board));
  }

  public static boolean solve(int[][] board)
  {
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        if (board[i][j] == 0) {
          for (int k = 1; k <= 9; k++) {
            if (isValid(i, j, k, board)) {
              board[i][j] = k;
              if (solve(board)) {
                return true;
              } else {
                board[i][j] = 0;
              }
            }
          }
          return false;
        }
      }
    }
    printArray(board);
    return true;
  }
}
