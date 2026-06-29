package github.com.pixayo.unimob.view;

import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.*;
import javafx.scene.text.Text;
import javafx.stage.*;
import java.io.InputStream;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;

public class MenuAcessibilidade {
    private static Popup p;
    private static double x, y, z = 1, dx, dy, tx, ty, b = 0;
    private static boolean c, l, d;
    private static ColorAdjust ef = new ColorAdjust();
    private static MediaPlayer mp;
    private static String css;

    static {
        try {
            Path t = Files.createTempFile("tema", ".css");
            Files.write(t, ".root{-bg1:#121212;-bg2:#1E1E1E;-bg3:#2B2B2B;-fg1:#FFF;-fg2:#E0E0E0;-fg3:#1E1E1E;-icon-light:#FFF;-icon-dark:#FFF;-text-light:#FFF;-text-dark:#FFF;-text-mute:#AAA;-fx-base:#121212;-fx-background:#121212;-fx-control-inner-background:#1E1E1E}Pane,VBox,HBox,GridPane,BorderPane,StackPane,ScrollPane,AnchorPane,#header,.search-container,.horizontal-scroll-container,.viewport{-fx-background-color:#121212}Label,Button,TextField,TextArea,Text,.label,.button,.text-field,.text-area,.text,#header .text-title,.section-title,.search-input{-fx-text-fill:#FFF;-fx-fill:#FFF}#footer .button,.capsule-toggle{-fx-background-color:#1E1E1E;-fx-text-fill:#FFF}#footer .button.selected,.capsule-toggle:selected{-fx-background-color:#2B2B2B;-fx-text-fill:#FFF}.text-muted{-fx-text-fill:#AAA}.icon-svg,.search-container .icon-svg{-fx-fill:#FFF}.region-placeholder{-fx-background-color:#2B2B2B}".getBytes());
            t.toFile().deleteOnExit();
            css = t.toUri().toString();
        } catch (Exception ignored) {}

        Window.getWindows().forEach(w -> {
            w.sceneProperty().addListener((o, os, ns) -> { if (ns != null) cfg(ns); });
            if (w.getScene() != null) cfg(w.getScene());
        });

        Window.getWindows().addListener((ListChangeListener<Window>) ch -> {
            while (ch.next()) if (ch.wasAdded()) ch.getAddedSubList().forEach(w -> {
                w.sceneProperty().addListener((o, os, ns) -> { if (ns != null) cfg(ns); });
                if (w.getScene() != null) cfg(w.getScene());
            });
        });
    }

    public static void toggle(boolean show, Window owner) {
        if (p == null) {
            p = new Popup();
            VBox m = new VBox(15);
            m.setAlignment(Pos.CENTER);

            m.setStyle("-fx-background-color:#175898;-fx-padding:15;-fx-background-radius:10;-fx-border-color:#FFF;-fx-border-radius:10;-fx-border-width:2;-fx-cursor:move;");
            m.setOnMousePressed(e -> { x = e.getScreenX() - p.getX(); y = e.getScreenY() - p.getY(); });
            m.setOnMouseDragged(e -> { p.setX(e.getScreenX() - x); p.setY(e.getScreenY() - y); });

            Button bl = btn("Leitor", () -> {});
            bl.setOnAction(e -> {
                l = !l;
                bl.setStyle(l ? "-fx-background-color:#2ecc71;-fx-text-fill:#FFF;-fx-font-weight:bold;-fx-font-size:20;" : "-fx-background-color:transparent;-fx-text-fill:#FFF;-fx-font-weight:bold;-fx-font-size:20;");
                seFalar("Leitor " + (l ? "Ativado" : "Desativado"));
            });

            Label lblZoom = new Label("Zoom: " + Math.round(z * 100) + "%");
            lblZoom.setStyle("-fx-text-fill: #FFF; -fx-font-weight: bold; -fx-font-size: 20;");

            Label lblBrilho = new Label("Brilho: " + Math.round(b * 100) + "%");
            lblBrilho.setStyle("-fx-text-fill: #FFF; -fx-font-weight: bold; -fx-font-size: 20;");

            HBox boxZoom = new HBox(15,
                    btn("A-", () -> { if ((z -= 0.1) <= 1) z=1; att(); lblZoom.setText("Zoom: " + Math.round(z * 100) + "%"); seFalar("Menor"); }),
                    lblZoom,
                    btn("A+", () -> { if ((z += 0.1) >= 2) z=2; att(); lblZoom.setText("Zoom: " + Math.round(z * 100) + "%"); seFalar("Maior"); })
            );
            boxZoom.setAlignment(Pos.CENTER);

            HBox boxBrilho = new HBox(15,
                    btn("B-", () -> { if ((b -= 0.1) <= -0.8) b=-0.8; att(); lblBrilho.setText("Brilho: " + Math.round(b * 100) + "%"); seFalar("Menos Brilho"); }),
                    lblBrilho,
                    btn("B+", () -> { if ((b += 0.1) >= 0.8) b=0.8; att(); lblBrilho.setText("Brilho: " + Math.round(b * 100) + "%"); seFalar("Mais Brilho"); })
            );
            boxBrilho.setAlignment(Pos.CENTER);

            m.getChildren().addAll(
                    boxZoom,
                    boxBrilho,
                    btn("Contraste", () -> { c = !c; att(); seFalar("Contraste alterado"); }),
                    btn("Tema Escuro", () -> { d = !d; att(); seFalar("Tema alterado"); }),
                    bl
            );
            p.getContent().add(m);
        }
        if (show && owner != null) { if (!p.isShowing()) p.show(owner, owner.getX()+280, owner.getY()+100); att(); }
        else if (p != null) p.hide();
    }

    private static Button btn(String txt, Runnable ac) {
        Button b = new Button(txt);
        b.setStyle("-fx-background-color:transparent;-fx-text-fill:#FFF;-fx-font-weight:bold;-fx-font-size:20;-fx-cursor:hand;");
        b.setOnAction(e -> ac.run());
        return b;
    }

    private static void att() {
        Window.getWindows().stream().filter(w -> w.getScene() != null).forEach(w -> cfg(w.getScene()));
    }

    public static void cfg(Scene s) {
        if (s == null || s.getRoot() == null) return;
        Node r = s.getRoot();

        boolean isPopup = (p != null && s == p.getScene());
        double currentZ = isPopup ? 1.0 : z;

        if (currentZ <= 1) { r.setTranslateX(0); r.setTranslateY(0); }
        r.setScaleX(currentZ); r.setScaleY(currentZ);
        ef.setBrightness(b); ef.setContrast(c ? 0.6 : 0.0);
        r.setEffect(ef);

        if (css != null) { s.getStylesheets().remove(css); if (d) s.getStylesheets().add(css); }

        if (!isPopup && s.getProperties().putIfAbsent("cfg", true) == null) {
            s.addEventFilter(MouseEvent.MOUSE_PRESSED, e -> { if (z>1) { dx=e.getScreenX(); dy=e.getScreenY(); tx=r.getTranslateX(); ty=r.getTranslateY(); }});
            s.addEventFilter(MouseEvent.MOUSE_DRAGGED, e -> { if (z>1) { double lx = s.getWidth()*(z-1)/2, ly = s.getHeight()*(z-1)/2; r.setTranslateX(Math.max(-lx, Math.min(lx, tx+e.getScreenX()-dx))); r.setTranslateY(Math.max(-ly, Math.min(ly, ty+e.getScreenY()-dy))); }});
            s.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
                if (!l) return;
                Node n = (Node) e.getTarget(); String t = null;
                while (n != null && t == null) {
                    if (n instanceof Labeled) t = ((Labeled)n).getText();
                    else if (n instanceof Text) t = ((Text)n).getText();
                    else if (n instanceof TextInputControl) t = ((TextInputControl)n).getText().isEmpty() ? ((TextInputControl)n).getPromptText() : ((TextInputControl)n).getText();
                    if (t != null && !t.trim().isEmpty()) break;
                    n = n.getParent();
                }
                seFalar(t);
            });
        }
    }

    public static void seFalar(String txt) {
        if (!l || txt == null || txt.trim().isEmpty()) return;
        new Thread(() -> {
            try {
                HttpURLConnection cx = (HttpURLConnection) new URL("https://translate.google.com/translate_tts?ie=UTF-8&tl=pt-BR&client=tw-ob&q=" + URLEncoder.encode(txt, StandardCharsets.UTF_8)).openConnection();
                cx.setRequestProperty("User-Agent", "Mozilla/5.0");
                Path tmp = Files.createTempFile("tts", ".mp3");
                try (InputStream in = cx.getInputStream()) { Files.copy(in, tmp, StandardCopyOption.REPLACE_EXISTING); }
                Platform.runLater(() -> {
                    if (mp != null) mp.stop();
                    (mp = new MediaPlayer(new Media(tmp.toUri().toString()))).setOnEndOfMedia(() -> { try { Files.deleteIfExists(tmp); } catch (Exception ex) {} });
                    mp.play();
                });
            } catch (Exception ex) {}
        }).start();
    }
}