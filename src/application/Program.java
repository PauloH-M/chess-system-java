package application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import ches.ChessException;
import ches.ChessMatch;
import ches.ChessPiece;
import ches.ChessPosition;



public class Program {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println();
		System.out.println();
		System.out.println("                 uuuuuuu\r\n"
					+ "             uu$$$$$$$$$$$uu\r\n"
					+ "          uu$$$$$$$$$$$$$$$$$uu\r\n"
					+ "         u$$$$$$$$$$$$$$$$$$$$$u\r\n"
					+ "        u$$$$$$$$$$$$$$$$$$$$$$$u\r\n"
					+ "       u$$$$$$$$$$$$$$$$$$$$$$$$$u\r\n"
					+ "       u$$$$$$$$$$$$$$$$$$$$$$$$$u\r\n"
					+ "       u$$$$$$\"   \"$$$\"   \"$$$$$$u\r\n"
					+ "       \"$$$$\"      u$u       $$$$\"\r\n"
					+ "        $$$u       u$u       u$$$\r\n"
					+ "        $$$u      u$$$u      u$$$\r\n"
					+ "         \"$$$$uu$$$   $$$uu$$$$\"\r\n"
					+ "          \"$$$$$$$\"   \"$$$$$$$\"\r\n"
					+ "            u$$$$$$$u$$$$$$$u\r\n"
					+ "             u$\"$\"$\"$\"$\"$\"$u\r\n"
					+ "  uuu        $$u$  P.H  $u$$       uuu\r\n"
					+ " u$$$$        $$$$$u$u$u$$$       u$$$$\r\n"
					+ "  $$$$$uu      \"$$$$$$$$$\"     uu$$$$$$\r\n"
					+ "u$$$$$$$$$$$uu    \"\"\"\"\"    uuuu$$$$$$$$$$\r\n"
					+ "$$$$\"\"\"$$$$$$$$$$uuu   uu$$$$$$$$$\"\"\"$$$\"\r\n"
					+ " \"\"\"      \"\"$$$$$$$$$$$uu \"\"$\"\"\"\r\n"
					+ "           uuuu \"\"$$$$$$$$$$uuu\r\n"
					+ "  u$$$uuu$$$$$$$$$uu \"\"$$$$$$$$$$$uuu$$$\r\n"
					+ "  $$$$$$$$$$\"\"\"\"           \"\"$$$$$$$$$$$\"\r\n"
					+ "   \"$$$$$\"                      \"\"$$$$\"\"\r\n"
					+ "     $$$\"                         $$$$\"");
		System.out.println("           QUE OS JOGOS COMECEM");
		System.out.println("                 PH GAMER");
		ChessMatch chessMatch = new ChessMatch();
		List<ChessPiece> captured = new ArrayList<>();
		
		while (!chessMatch.getCheckMate()) {
			try {
				UI.clearScreen();
				UI.printMatch(chessMatch, captured);
				System.out.println();
				System.out.print("Source: ");
				ChessPosition source = UI.readChessPosition(sc);
				
				boolean[][] possibleMoves = chessMatch.possibleMoves(source);
				UI.clearScreen();
				UI.printBoard(chessMatch.getPieces(), possibleMoves);
				System.out.println();
				System.out.print("Target: ");
				ChessPosition target = UI.readChessPosition(sc);
				
				ChessPiece capturedPiece = chessMatch.performChessMove(source, target);
				
				if (capturedPiece != null) {
					captured.add(capturedPiece);
				}
				
				if (chessMatch.getPromoted() != null) {
					System.out.print("Enter piece for promotion (B/N/R/Q): ");
					String type = sc.nextLine().toUpperCase();
					while (!type.equals("B") && !type.equals("N") && !type.equals("R") & !type.equals("Q")) {
						System.out.print("Invalid value!Enter piece for promotion (B/N/R/Q): ");
						type = sc.nextLine().toUpperCase();
					}
					chessMatch.replacePromotedPiece(type);
				}
			}
			catch (ChessException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
			catch (InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
		}
		UI.clearScreen();
		UI.printMatch(chessMatch, captured);
	}
}
