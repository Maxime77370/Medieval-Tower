package com.medievaltower.entities;

import com.medievaltower.entities.bloc.*;

/**
 * Constant class
 * <p>
 *     This class is used to create all constants.
 *     It is used in the Map class.
 * </p>
 * @see Bloc
 * @see IceBloc
 * @see SandBloc
 * @see FireBloc
 * @see BlocNormal
 */
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
