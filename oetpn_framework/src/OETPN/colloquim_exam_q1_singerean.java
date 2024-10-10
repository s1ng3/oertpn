package OETPN;

import java.util.ArrayList;

import Components.Activation;
import Components.Condition;
import Components.GuardMapping;
import Components.PetriNet;
import Components.PetriNetWindow;
import Components.PetriTransition;
import DataObjects.DataFloat;
import DataObjects.DataSubPetriNet;
import DataObjects.DataTransfer;
import DataOnly.SubPetri;
import DataOnly.TransferOperation;
import Enumerations.LogicConnector;
import Enumerations.TransitionCondition;
import Enumerations.TransitionOperation;

public class colloquim_exam_q1_singerean {

    public static void main(String[] args) {
        PetriNet pn = new PetriNet();
        pn.PetriNetName = "Main Petri";
        pn.NetworkPort = 1080;

        // ----------------------- sub petri ------------------------------------
        PetriNet spn = new PetriNet();
        spn.PetriNetName = "PN3";

        DataFloat subConstantValue1 = new DataFloat();
        subConstantValue1.SetName("subConstantValue1");
        subConstantValue1.SetValue(0.01f);
        spn.ConstantPlaceList.add(subConstantValue1);

        DataFloat p14 = new DataFloat();
        p14.SetName("p14");
        spn.PlaceList.add(p14);

        DataFloat p15 = new DataFloat();
        p15.SetName("p15");
        spn.PlaceList.add(p15);

        DataFloat p17 = new DataFloat();
        p17.SetName("p17");
        spn.PlaceList.add(p17);

        DataTransfer p16 = new DataTransfer();
        p16.SetName("p16");
        p16.Value = new TransferOperation("localhost", "1081", "p12");
        spn.PlaceList.add(p16);

        // Transition T10 --------------------------------------
        PetriTransition t10 = new PetriTransition(spn);
        t10.TransitionName = "t10";
        t10.InputPlaceName.add("p14");

        Condition T10Ct1 = new Condition(t10, "p14", TransitionCondition.NotNull);

        GuardMapping grdT10 = new GuardMapping();
        grdT10.condition = T10Ct1;

        ArrayList<String> lstInput = new ArrayList<String>();
        lstInput.add("p14");
        lstInput.add("subConstantValue1");
        grdT10.Activations.add(new Activation(t10, lstInput, TransitionOperation.Prod, "p15*0.05"));

        // Transition T11 --------------------------------------
        PetriTransition t11 = new PetriTransition(spn);
        t11.TransitionName = "t11";
        t11.InputPlaceName.add("p15");
        Condition T11Ct1 = new Condition(t11, "p15", TransitionCondition.NotNull);

        GuardMapping grdT11 = new GuardMapping();
        grdT11.condition = T11Ct1;
        grdT11.Activations.add(new Activation(t11, "p15", TransitionOperation.Move, "p17"));
        grdT11.Activations.add(new Activation(t11, "p15", TransitionOperation.SendOverNetwork, "p16"));
        grdT11.Activations.add(new Activation(t11, "p15", TransitionOperation.Move, "p16"));

        // Transition T12 --------------------------------------
        PetriTransition t12 = new PetriTransition(spn);
        t12.TransitionName = "t12";
        t12.InputPlaceName.add("p17");
        Condition T12Ct1 = new Condition(t12, "p17", TransitionCondition.NotNull);

        GuardMapping grdT12 = new GuardMapping();
        grdT12.condition = T12Ct1;

        grdT12.Activations.add(new Activation(t12, "p17", TransitionOperation.Move, "p14"));

        t12.GuardMappingList.add(grdT12);
        t12.Delay = 0;
        spn.Transitions.add(t12);
        spn.Delay = 3000;

        // ----------------------------------------------------
        DataSubPetriNet subPetriNet = new DataSubPetriNet();
        subPetriNet.SetName("SubPetri2");
        SubPetri sptr= new SubPetri(spn);
        subPetriNet.SetValue(sptr);
        pn.ConstantPlaceList.add(subPetriNet);

        DataFloat p0 = new DataFloat();
        p0.SetName("p0");
        p0.SetValue(1.0f);
        pn.PlaceList.add(p0);

        DataSubPetriNet p5 = new DataSubPetriNet();
        p5.SetName("p5");
        pn.PlaceList.add(p5);

        DataFloat p1 = new DataFloat();
        p1.SetName("p1");
        pn.PlaceList.add(p1);

        DataFloat p2 = new DataFloat();
        p2.SetName("p2");
        pn.PlaceList.add(p2);

        DataFloat p3 = new DataFloat();
        p3.SetName("p3");
        pn.PlaceList.add(p3);

        DataFloat p4 = new DataFloat();
        p4.SetName("p2");
        pn.PlaceList.add(p4);

        // Transition T0  --------------------------------------

        PetriTransition t0 = new PetriTransition(pn);
        t0.TransitionName = "t0";
        t0.InputPlaceName.add("p0");
        t0.InputPlaceName.add("p5");

        Condition T0Ct1 = new Condition(t0, "p0", TransitionCondition.NotNull);
        Condition T0Ct2 = new Condition(t0, "p5", TransitionCondition.NotNull);
        T0Ct1.SetNextCondition(LogicConnector.AND, T0Ct2);

        GuardMapping grdT0 = new GuardMapping();
        grdT0.condition = T0Ct1;

        t0.GuardMappingList.add(grdT0);
        t0.Delay = 0;
        pn.Transitions.add(t0);

        // Transition T1 ------------------------------------------

        PetriTransition t1 = new PetriTransition(pn);
        t1.TransitionName = "t1";
        t1.InputPlaceName.add("p1");

        Condition T1Ct1 = new Condition(t1, "p1", TransitionCondition.NotNull);

        GuardMapping grdT1 = new GuardMapping();
        grdT1.condition = T1Ct1;

        t0.GuardMappingList.add(grdT1);
        t0.Delay = 0;
        pn.Transitions.add(t1);

        // Transition T2 ------------------------------------------

        PetriTransition t2 = new PetriTransition(pn);
        t2.TransitionName = "t2";
        t2.InputPlaceName.add("p2");

        Condition T2Ct1 = new Condition(t2, "p2", TransitionCondition.NotNull);

        GuardMapping grdT2 = new GuardMapping();
        grdT2.condition = T2Ct1;

        grdT2.Activations.add(new Activation(t2, "SubPetri2", TransitionOperation.Move, "p3"));
        grdT2.Activations.add(new Activation(t2, "p3", TransitionOperation.Move, "p3-p14"));
        grdT2.Activations.add(new Activation(t2, "p3", TransitionOperation.ActivateSubPetri, ""));

        t2.GuardMappingList.add(grdT2);
        t2.Delay = 0;
        pn.Transitions.add(t2);

        // Transition T3 ------------------------------------------

        PetriTransition t3 = new PetriTransition(pn);
        t3.TransitionName = "t3";
        t3.InputPlaceName.add("p3");

        Condition T3Ct1 = new Condition(t3, "p3", TransitionCondition.NotNull);
        Condition T3Ct2 = new Condition(t3, "p3", TransitionCondition.SubPetriStopped);

        T3Ct1.SetNextCondition(LogicConnector.AND, T3Ct2);

        GuardMapping grdT3 = new GuardMapping();
        grdT3.condition = T3Ct1;
        grdT3.Activations.add(new Activation(t3, "p3-p14", TransitionOperation.Copy, "p4"));

        t3.GuardMappingList.add(grdT3);
        t3.Delay = 0;
        pn.Transitions.add(t3);

        // Transition T4 ------------------------------------------

        PetriTransition t4 = new PetriTransition(pn);
        t4.TransitionName = "t4";
        t4.InputPlaceName.add("p4");

        Condition T4Ct1 = new Condition(t4, "p4", TransitionCondition.NotNull);

        GuardMapping grdT4 = new GuardMapping();
        grdT4.condition = T4Ct1;
        grdT4.Activations.add(new Activation(t4, "p4", TransitionOperation.Move, "p0"));

        t4.GuardMappingList.add(grdT4);
        t4.Delay = 0;
        pn.Transitions.add(t4);

        PetriNetWindow frame = new PetriNetWindow(false);
        frame.petriNet = pn;
        frame.setVisible(true);

        // PN2 -------------------------------------------------

        PetriNet pn2 = new PetriNet();
        pn2.PetriNetName = "PN2";
        pn2.NetworkPort = 1081;

        DataFloat p7 = new DataFloat();
        p7.SetName("p7");
        p7.SetValue(1.0f);

        pn2.PlaceList.add(p7);

        DataFloat p8 = new DataFloat();
        p8.SetName("p8");
        pn2.PlaceList.add(p8);

        DataFloat p9 = new DataFloat();
        p9.SetName("p9");
        pn2.PlaceList.add(p9);

        DataFloat p12 = new DataFloat();
        p12.SetName("p12");
        pn2.PlaceList.add(p12);

        // Transition T5 ------------------------------------------

        PetriTransition t5 = new PetriTransition(pn2);
        t5.TransitionName = "t5";
        t5.InputPlaceName.add("p7");
        t5.InputPlaceName.add("p12");

        Condition T5Ct1 = new Condition(t5, "p7", TransitionCondition.NotNull);
        Condition T5Ct2 = new Condition(t5, "p12", TransitionCondition.NotNull);

        T5Ct1.SetNextCondition(LogicConnector.AND, T5Ct2);

        GuardMapping grdT5 = new GuardMapping();
        grdT5.condition = T5Ct1;
        grdT5.Activations.add(new Activation(t5, "p7", TransitionOperation.Move, "p8"));

        t5.GuardMappingList.add(grdT5);
        t5.Delay = 0;
        pn2.Transitions.add(t5);

        // Transition T6 ------------------------------------------

        PetriTransition t6 = new PetriTransition(pn2);
        t6.TransitionName = "t6";
        t6.InputPlaceName.add("p8");

        Condition T6Ct1 = new Condition(t6, "p8", TransitionCondition.NotNull);

        GuardMapping grdT6 = new GuardMapping();
        grdT6.condition = T6Ct1;
        grdT6.Activations.add(new Activation(t6, "p8", TransitionOperation.Move, "p9"));

        t6.GuardMappingList.add(grdT6);
        t6.Delay = 0;
        pn2.Transitions.add(t6);

        // Transition T7 ------------------------------------------

        PetriTransition t7 = new PetriTransition(pn2);
        t7.TransitionName = "t7";
        t7.InputPlaceName.add("p9");

        Condition T7Ct1 = new Condition(t7, "p9", TransitionCondition.NotNull);

        GuardMapping grdT7 = new GuardMapping();
        grdT7.condition = T7Ct1;
        grdT7.Activations.add(new Activation(t7, "p9", TransitionOperation.Move, "p7"));

        t7.GuardMappingList.add(grdT7);
        t7.Delay = 0;
        pn2.Transitions.add(t7);

        PetriNetWindow frame2 = new PetriNetWindow(false);
        frame2.petriNet = pn2;
        frame2.setVisible(true);

        //NOTE: It needs fix. The code is not working as expected.
    }
}