package fr.istic.mmm.battlesnake.fragments.gameboard;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.istic.mmm.battlesnake.R;
import fr.istic.mmm.battlesnake.model.Direction;
import fr.istic.mmm.battlesnake.model.Game;
import fr.istic.mmm.battlesnake.model.Player;
import fr.istic.mmm.battlesnake.socket.Server;
import fr.istic.mmm.battlesnake.view.CustomViewBoard;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GameboardFragmentSolo.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GameboardFragmentSolo#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GameboardFragmentSolo extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "GameboardFragmentSolo";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    @BindView(R.id.boardView) CustomViewBoard boardView;

    @BindView(R.id.buttonDown)
    Button buttonDown;
    @BindView(R.id.buttonUp)
    Button buttonUp;
    @BindView(R.id.buttonLeft)
    Button buttonLeft;
    @BindView(R.id.buttonRight)
    Button buttonRight;


    private Game game;
    private Player player;

    public GameboardFragmentSolo() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GameboardFragmentSolo.
     */
    // TODO: Rename and change types and number of parameters
    public static GameboardFragmentSolo newInstance(String param1, String param2) {
        GameboardFragmentSolo fragment = new GameboardFragmentSolo();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gameboard, container, false);
        ButterKnife.bind(this, view);

        Server serv = new Server(1);
        new Thread(serv).start();

        //TODO récupéré l'objet board du serveur a la place
        game = new Game();


        //TODO récupéré l'objet joueur depuis la socket
        player = game.addNewPlayer();
        game.addNewPlayer();
        game.startGame();

        boardView.drawBoard(game.getBoardCells());
        buttonUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onUpPressed();
            }
        });

        buttonDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onDownPressed();
            }
        });

        buttonLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onLeftPressed();
            }
        });

        buttonRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onRightPressed();
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

    public void onUpPressed(){
        Log.i(TAG,"up pressed");
        game.moveSnakePlayer(Direction.TOP,player.getPlayerId());
        boardView.drawBoard(game.getBoardCells());
    }

    public void onDownPressed(){
        Log.i(TAG,"down pressed");
        game.moveSnakePlayer(Direction.BOT,player.getPlayerId());
        boardView.drawBoard(game.getBoardCells());
    }

    public void onLeftPressed(){
        Log.i(TAG,"left pressed");
        game.moveSnakePlayer(Direction.LEFT,player.getPlayerId());
        boardView.drawBoard(game.getBoardCells());
    }

    public void onRightPressed(){
        Log.i(TAG,"right pressed");
        game.moveSnakePlayer(Direction.RIGHT,player.getPlayerId());
        boardView.drawBoard(game.getBoardCells());
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void setBoardView(CustomViewBoard boardView) {
        this.boardView = boardView;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
