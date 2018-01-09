package org.test;

import com.vaadin.annotations.Theme;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.renderers.ImageRenderer;

import java.util.stream.IntStream;

@Theme("mytheme")
public class VaadinUI extends UI {

    private Grid<Integer> grid = new Grid<>();

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        grid.addColumn(i -> i).setCaption("Number");
        addIconColumn();
        grid.setItems(IntStream.range(1, 21).boxed());

        final VerticalLayout layout = new VerticalLayout(grid);
        setContent(layout);
    }

    private void addIconColumn() {
        ImageRenderer<Integer> renderer = new ImageRenderer<>();
        renderer.addClickListener(e -> iconClicked(e.getItem()));

        Grid.Column<Integer, ThemeResource> iconColumn =
                grid.addColumn(i -> new ThemeResource("img/icon.svg"), renderer);

        grid.addItemClickListener(e -> {
            if (e.getColumn().equals(iconColumn)) {
                iconClicked(e.getItem());
            }
        });
    }

    private void iconClicked(Integer i) {
        Notification.show(i + " clicked!");
    }

}
