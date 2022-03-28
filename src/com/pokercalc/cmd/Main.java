package com.pokercalc.cmd;

import java.util.*;

public class Main
{
    public static Card[] deckMain;
    public static HashMap<Integer,String> hashmapRanksMain;

    public static ArrayList<Card> arraylistSelectedCards;
    public static Card[] userHand;

    public static final int numberOfPlayers;
    public static Card[][] otherPlayersHands;


    static
    {
        System.out.println("Start static init");

        hashmapRanksMain = Poker.getHashMapRanks();
        deckMain = Poker.getPokerDeck();
        arraylistSelectedCards = new ArrayList<Card>();
        userHand = new Card[2];
        numberOfPlayers = inputNumberOfPlayers();
        otherPlayersHands = new Card[numberOfPlayers][2];
        System.out.println("End static init");
    }



    public static void main(String[] args)
    {

        while (true) {

            Card userCard1 = inputCard(1, true);            //  USER, first card
            if(userCard1 == null){
    //TODO: ERROR MESSAGE about null card object
                continue;
            }

            Card userCard2 = inputCard(2,true);             //  USER, second card
            if(userCard2 != null){
                break;

            }else{  /*TODO: ERROR MESSAGE about null card object*/  }
        }
    }



//  gets input from user to set number of players/hands
    public static int inputNumberOfPlayers()
    {

//  while loop used for input validation; loop continues until user enters a valid integer, 2 - 10
        while (true) {

            int numberOfPlayers = 0;

//  user input wrapped in try-catch to use thrown exceptions catch-blocks for printing error messages and continuing while loop
            try {
                System.out.println("Input the number of users: ");
                Scanner scanner = new Scanner(System.in);
                numberOfPlayers = scanner.nextInt();

//  Must have more than 1 players; if user inputs '1' then an exception is thrown, an error message is printed,
//  and the while loop is continued
                if(numberOfPlayers <= 1){
                    throw new Exception("ERROR: Invalid input\nNumber of players must be greater than 1.\nTry again.");
                }

//  Max number of players is 10; does the same as if block above for '10'
                if (numberOfPlayers > 10){
                    throw new Exception("ERROR: Invalid input\nNumber of players must be less than or equal to 10.\nTry again.");
                }

//  catch block for non-numeric input
            } catch (InputMismatchException ime) {
                System.out.println("ERROR: InputMismatchException thrown\nInput must be an integer. Try again.\n");
                continue;

//  generic Exception catch block used for <=1 / >10 input validation above
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }

            return numberOfPlayers;
        }
    }



//  TODO: utilize function params to change input instructions/println based on whose card is being input/whether its their first or second
    //  TODO: [?] use overridden function instead [?] -- one param for user for card number; second int 'playerCtr' param for others
    public static Card inputCard(int cardNumber, boolean isUser)
    {
        String inputSuit = null;
        String inputStrRank = null;

        try {
            WHILE_SUIT: while (true)
            {
                System.out.println("Input the [SUIT] (e.g. 'Hearts' or 'clubs') for the first card in your hand: ");
                Scanner scanner = new Scanner(System.in);
                String scanSuit = scanner.next().trim().toUpperCase();

                for(Poker.ENUM_SUITS suit : Poker.ENUM_SUITS.values())
                {
                    if(scanSuit.equals(suit.toString())) {
                        inputSuit = suit.toString();
                        break WHILE_SUIT;
                    }
                }
                System.out.println("\n\nERROR: Input did not match any Suit\nEnter 'Clubs' 'Hearts' 'Diamonds' or 'Spades'\nTry again.\n\n");
            }

            while (true) {
                System.out.println("Input the [RANK] spelled out, NOT as a number (e.g. 'Ace' or 'Two') for the first card in your hand: ");
                Scanner scanner = new Scanner(System.in);
                String scanStrRank = scanner.next();

                if (!isNumeric(scanStrRank)) {
                    inputStrRank = scanStrRank.trim().toUpperCase();
                    break;

                } else {
                    System.out.println("ERROR: Input contained integer(s)\nInput card rank as a String, e.g. 'TWO' or 'three.' Try again.");
                }
            }

            if(inputStrRank != null && inputSuit != null) {
                return new Card(inputStrRank, inputSuit, hashmapRanksMain);
            }

            else{
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


//  tests if a string is a number by seeing if it can be parsed into an int.
//  Returns TRUE if the string can be parsed into an int; returns FALSE if it cannot.
//  Used for user input of card ranks in function inputCard()
    public static boolean isNumeric(String s)
    {
        int i;

//  if string 's' is null or empty an error message is printed and function returns false
        if(s == null || s.equals("")){
            System.out.println("String is null/empty; can't be parsed.");
            return false;
        }

//  parse string as int wrapped in try-catch; if string can't be parsed as int, NumberFormatException is caught
//  and a notification is printed to console
        try {

//  if 's' is parsed as int and no NFE is thrown, the string is a number; function returns true
            i = Integer.parseInt(s);
            return true;

        }catch (NumberFormatException nfe){
            System.out.println("NumberFormatException thrown.\nInput string can't be parsed to integer/is NOT numeric.");
        }

//  return false after catch block; NFE was thrown, 's' is not numeric
        return false;
    }


}
