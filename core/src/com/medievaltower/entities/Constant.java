package com.medievaltower.entities;

import com.medievaltower.entities.bloc.*;

public class Constant {

    public Bloc newBloc(int id, int x, int y){
        switch (id){
            case 1:
                return new IceBloc(x, y);   // Ice bloc id 1
            case 2:
                return new SandBloc(x, y);  // Sand bloc id 2
            case 3:
                return new FireBloc(x, y);  // Fire bloc id 3
            default:
                return new BlocNormal(x, y); // Normal by default
        }

    }
}
