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


//  Static block calls various functions to initiate global vars
    static
    {
        hashmapRanksMain = Poker.getHashMapRanks();
        deckMain = Poker.getPokerDeck();

//  create an arraylist of Card objects; used to track which cards have already been input by the user
        arraylistSelectedCards = new ArrayList<Card>();

//  an array of Card objects used to represent the user's hand
        userHand = new Card[2];

        numberOfPlayers = inputNumberOfPlayers();

//  a 3D array of Card objects used to represent all other players' hands
        otherPlayersHands = new Card[numberOfPlayers][2];
    }


    public static void main(String[] args)
    {
        int ctrCards = 0;
        for(Card userCard: userHand)
        {
//  while loop validates Card input by user hasn't already been input/isn't already in arraylistSelectedCards
            WHILE: while (true)
            {
                Card test_inputCard = inputCard();

//  if this isn't the first card being input AND isAlreadySelected() returns true when passed the card the user just input
                if(ctrCards > 0 && isAlreadySelected(test_inputCard))
                {
//  output error message and continue loop to force user to input a different Suit-Rank combination
                    outputErrorMessage("Duplicate Card Entered",
                            "A card has already been input with this Suit-Rank combination",
                            true);

                    continue WHILE;
                }

                else
                {
                    userHand[ctrCards] = test_inputCard;
                    arraylistSelectedCards.add(userHand[ctrCards]);
                    break WHILE;
                }
            }

            System.out.println("\n\n---------------------------\n" +
                    "Added " + userHand[ctrCards].getRankStr() + " of " + userHand[ctrCards].getSuit() + " to hand.");

            ctrCards++;
        }


//  TODO: documentation above why other players cards would be known
//      --  calculate specific probabilities of your hand winning vs other hands
//      --  j

        if (areOtherPlayersCardsKnown)
        {
            ctrCards = 0;
            int ctrHands = 0;

//  outer foreach loop for players' hands
            for(Card[] playerHand : otherPlayersHands)
            {
//  inner foreach loop for cards in each hand
                for(Card playerCard : playerHand)
                {
                    otherPlayersHands[ctrHands][ctrCards] = inputCard();
                    arraylistSelectedCards.add(otherPlayersHands[ctrHands][ctrCards]);

                    ctrCards++;
                }

//  set card ctr var back to 0 and increment hand ctr var before next outer foreach iteration
                ctrCards = 0;
                ctrHands++;
            }
        }



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

                //TODO: implement isNumeric() to validate input?

//  Must have more than 1 players; if user inputs '1' then an exception is thrown, an error message is printed,
//  and the while loop is continued
                if(numberOfPlayers <= 1){
                    //throw new Exception("ERROR: Invalid input\nNumber of players must be greater than 1.\nTry again.");
                    outputErrorMessage("Invalid Input for Number of Players",
                            "Number of players must be greater than 1.",
                            true);
                    continue;
                }

//  Max number of players is 10; does the same as if block above for '10'
                if (numberOfPlayers > 10){
                    //throw new Exception("ERROR: Invalid input\nNumber of players must be less than or equal to 10.\nTry again.");
                    outputErrorMessage("Invalid Input for Number of Players",
                            "Number of players must be less than or equal to 10.",
                            true);
                    continue;
                }

//  catch block for non-numeric input
            } catch (InputMismatchException ime) {
                outputErrorMessage("InputMismatchException thrown",
                        "Input must be an integer.",
                        true);
                continue;

//  generic Exception catch block
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

                outputErrorMessage("Invalid Suit Value Input",
                        "Input did not match any valid suit values.\n" +
                                "Suit MUST be 'HEARTS' 'DIAMONDS' SPADES' or 'CLUBS'",
                        true);
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

                //TODO: MISSING FOREACH ENUM_RANKS ???
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



/** ------------------------------------
 *      inputCard()         [O/R -- no params]
 *  ------------------------------------
 *
 **/

    public static Card inputCard()
    {
        String inputSuit = null;
        String inputStrRank = null;

        try {
            WHILE_SUIT: while(true)
            {
                System.out.println("""
                        \n\n----------------------
                        Input card Suit
                        E.g., 'HEARTS' or 'SPADES'
                        
                        Suit:   """);

                Scanner scanner = new Scanner(System.in);
                String strInput = scanner.next().trim().toUpperCase();

                for(Poker.ENUM_SUITS suit: Poker.ENUM_SUITS.values())
                {
                    if(strInput.equals(suit.toString())){
                        inputSuit = suit.toString();
                        break WHILE_SUIT;
                    }
                }

                outputErrorMessage("Invalid Suit Value Input",
                        "Input did not match any valid suit values.\n" +
                                "Suit MUST be 'HEARTS' 'DIAMONDS' SPADES' or 'CLUBS'",
                        true);
            }

            WHILE_RANK: while (true)
            {
                System.out.println("""
                        \n\n----------------------
                        Input card Rank as a String
                        E.g., 'ACE' or 'THREE'
                        
                        Rank:   """);

                Scanner scanner = new Scanner(System.in);
                String strInput = scanner.next().trim().toUpperCase();

                for(Poker.ENUM_RANKS rank : Poker.ENUM_RANKS.values())
                {
                    if(strInput.equals(rank.toString())){
                        inputStrRank = rank.toString();
                        break WHILE_RANK;
                    }
                }

                if(isNumeric(strInput)){
                    outputErrorMessage("Numeric Rank Value Input",
                            "Input must be rank spelled out, e.g. 'ACE' or 'TWO' -- No numbers",
                            true);

                    continue;
                }

            //TODO: generic err msg?
                outputErrorMessage("",
                        "",
                        true);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return new Card(inputStrRank,inputSuit,hashmapRanksMain);
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
 *      //todo: documentation
 **/

    public static boolean inputAreOtherPlayersCardsKnown()
    {
        int inputBoolInt = -1;

        try {
            while (true) {
                System.out.println("""
                        Input whether or not OTHER players cards are known:\s
                        Enter '0' if OTHER players cards are NOT known.
                        Enter '1' if OTHER players cards ARE known.\n""");

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
