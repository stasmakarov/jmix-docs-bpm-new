package com.company.onboarding.view.component.twincolumn;


import com.company.onboarding.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.StandardView;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;

@Route(value = "twin-column-view", layout = MainView.class)
@ViewController("TwinColumnView")
@ViewDescriptor("twin-column-view.xml")
public class TwinColumnView extends StandardView {

}