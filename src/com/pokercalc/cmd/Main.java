package com.pokercalc.cmd;

import java.util.*;

import static com.pokercalc.cmd.Utility.isNumeric;
import static com.pokercalc.cmd.Utility.outputErrorMessage;

public class Main
{
    public static Card[] deckMain;
    public static HashMap<Integer,String> hashmapRanksMain;

    public static ArrayList<Card> arraylistSelectedCards;
    public static Card[] userHand;

    public static final int numberOfPlayers;
    public static Card[][] otherPlayersHands;
    public static boolean areOtherPlayersCardsKnown;


    static
    {
        hashmapRanksMain = Poker.getHashMapRanks();
        deckMain = Poker.getPokerDeck();
        arraylistSelectedCards = new ArrayList<Card>();
        userHand = new Card[2];
        numberOfPlayers = inputNumberOfPlayers();
        otherPlayersHands = new Card[numberOfPlayers][2];
    }


    public static void main(String[] args)
    {
        while (true)
        {
            Card userCard1 = inputCard(1, true);            //  USER, first card

            if(userCard1 == null)
            {
                outputErrorMessage("Null Card Object",
                        "User Card object returned null. ",
                        true);

                continue;
            }

            arraylistSelectedCards.add(userCard1);
            userHand[0] = userCard1;
            break;                                                      // Break WHILE loop for user card 1
        }

        while (true)
        {
            Card userCard2 = inputCard(2,true);             //  USER, second card

            if(userCard2 == null)
            {
                outputErrorMessage("Null Card Object",
                        "User Card object returned null. ",
                        true);

                continue;
            }

            else if(isAlreadySelected(userCard2))
            {
                outputErrorMessage("Duplicate Card Input",
                        "A card with this Suit-Rank pair has already been input.",
                        true);

                continue;
            }

            arraylistSelectedCards.add(userCard2);
            userHand[1] = userCard2;
            break;
            }


//  TODO: get other players cards from user



    }



/** ------------------------
 *  inputNumberOfPlayers()
 *  ------------------------
 *      - gets input from user to set number of players/hands
 **/

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



/** ------------------------
 *      inputCard()
 *  ------------------------
 *
 **/

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

            if(inputSuit != null) {
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



/** ------------------------
 *  isAlreadySelected()
 *  ------------------------
 *     -- iterates Card objects in arraylistSelectedCards and compares Suit and Rank attributes to see if the Suit/Rank pair has
 *     already been input by the user.
 *     -- Returns true if the input card's Suit/Rank pair matches any card found in arraylistSelectedCards,
 *     else returns false
 **/

    public static boolean isAlreadySelected(Card inputCard)
    {
        for(Card c : arraylistSelectedCards)
        {
            if(Objects.equals(inputCard.getSuit(), c.getSuit()))
            {
                if(inputCard.getRank() == c.getRank())      {return true;}
            }
        }
        return false;
    }



/** ------------------------
 *  inputAreOtherPlayersCardsKnown()
 *  ------------------------
 *
 **/

    public static boolean inputAreOtherPlayersCardsKnown()
    {
        int inputBoolInt = -1;

        try {
            while (true) {
                System.out.println("""
                        Input whether or not OTHER players cards are known:\s
                        Enter '0' if OTHER players cards are NOT known.
                        Enter '1' if OTHER players cards ARE known.""");

                //TODO: create a function for scanner/input menus that works like outputErrorMessage() <?>

                Scanner s = new Scanner(System.in);
                inputBoolInt = s.nextInt();

                if(inputBoolInt != 1 && inputBoolInt != 0)
                {
                    outputErrorMessage("Invalid Input Entered",
                            "Input must be either a '0' or a '1'",
                            true);
                    continue;
                }

                else    {break;}
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (inputBoolInt == 1)      {return true;}

        else return false;
    }
}
