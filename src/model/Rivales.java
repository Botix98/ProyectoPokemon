package model;

public enum Rivales {
	CYRUS(1, "Cyrus", "C:/ProyectoPokemon/img/rivales/cyrus.png"),
    JACQ(2, "Jacq", "C:/ProyectoPokemon/img/rivales/jacq.png"),
    DANA(3, "Dana", "C:/ProyectoPokemon/img/rivales/dana.png"),
    MIKU_FAIRY(4, "Miku", "C:/ProyectoPokemon/img/rivales/miku-fairy.png"),
    OAK(5, "Oak", "C:/ProyectoPokemon/img/rivales/oak.png"),
    TUCKER(6, "Tucker", "C:/ProyectoPokemon/img/rivales/tucker.png"),
    IONO(7, "Iono", "C:/ProyectoPokemon/img/rivales/iono.png"),
    LUSAMINE_NIHILEGO(8, "Lusamine Nihilego", "C:/ProyectoPokemon/img/rivales/lusamine-nihilego.png"),
    LEON(9, "Leon", "C:/ProyectoPokemon/img/rivales/leon.png"),
    CYNTHIA(10, "Cynthia", "C:/ProyectoPokemon/img/rivales/cynthia.png"),
    OLYMPIA(11, "Olympia", "C:/ProyectoPokemon/img/rivales/olympia.png"),
    VALERIE(12, "Valerie", "C:/ProyectoPokemon/img/rivales/valerie.png"),
    WIKSTROM(13, "Wikstrom", "C:/ProyectoPokemon/img/rivales/wikstrom.png"),
    PLASMAGRUNT_GEN5BW(14, "Plasmagrunt", "C:/ProyectoPokemon/img/rivales/plasmagrunt-gen5bw.png"),
    AARUNE(15, "Aarune", "C:/ProyectoPokemon/img/rivales/aarune.png"),
    BRASSIUS(16, "Brassius", "C:/ProyectoPokemon/img/rivales/brassius.png"),
    BLANCHE(17, "Blanche", "C:/ProyectoPokemon/img/rivales/blanche.png"),
    CHEF(18, "Chef", "C:/ProyectoPokemon/img/rivales/chef.png"),
    CLIFF(19, "Cliff", "C:/ProyectoPokemon/img/rivales/cliff.png"),
    CONCORDIA(20, "Concordia", "C:/ProyectoPokemon/img/rivales/concordia.png"),
    DRAYTON(21, "Drayton", "C:/ProyectoPokemon/img/rivales/drayton.png"),
    GINTER(22, "Ginter", "C:/ProyectoPokemon/img/rivales/ginter.png"),
    MAGNUS(23, "Magnus", "C:/ProyectoPokemon/img/rivales/magnus.png"),
    MATEO(24, "Mateo", "C:/ProyectoPokemon/img/rivales/mateo.png"),
    NANCY(25, "Nancy", "C:/ProyectoPokemon/img/rivales/nancy.png"),
    RHI(26, "Rhi", "C:/ProyectoPokemon/img/rivales/rhi.png"),
    RYE(27, "Rye", "C:/ProyectoPokemon/img/rivales/rye.png"),
    SKYTRAINER(28, "Skytrainer", "C:/ProyectoPokemon/img/rivales/skytrainer.png"),
    BALLGUY(29, "Ballguy", "C:/ProyectoPokemon/img/rivales/ballguy.png"),
    SPENSER(30, "Spenser", "C:/ProyectoPokemon/img/rivales/spenser.png"),
    SURFER(31, "Surfer", "C:/ProyectoPokemon/img/rivales/surfer.png"),
    GIOVANNI(32, "Giovanni", "C:/ProyectoPokemon/img/rivales/giovanni.png"),
    TABITHA(33, "Tabitha", "C:/ProyectoPokemon/img/rivales/tabitha.png"),
    VINCE(34, "Vince", "C:/ProyectoPokemon/img/rivales/vince.png"),
    ASH(35, "Ash", "C:/ProyectoPokemon/img/rivales/ash.png"),
    ARTIST(36, "Artist", "C:/ProyectoPokemon/img/rivales/artist.png"),
    JUAN(37, "Juan", "C:/ProyectoPokemon/img/rivales/juan.png"),
    BIKER(38, "Biker", "C:/ProyectoPokemon/img/rivales/biker.png"),
    BLUE(39, "Blue", "C:/ProyectoPokemon/img/rivales/blue.png"),
    BROCK(40, "Brock", "C:/ProyectoPokemon/img/rivales/brock.png"),
    BUGSY(41, "Bugsy", "C:/ProyectoPokemon/img/rivales/bugsy.png"),
    BUGCATCHER(42, "Bugcatcher", "C:/ProyectoPokemon/img/rivales/bugcatcher.png"),
    CAITLIN(43, "Caitlin", "C:/ProyectoPokemon/img/rivales/caitlin.png"),
    CAMERAMAN(44, "Cameraman", "C:/ProyectoPokemon/img/rivales/cameraman.png"),
    STEVEN(45, "Steven", "C:/ProyectoPokemon/img/rivales/steven.png"),
    TEAMROCKET(46, "Team Rocket", "C:/ProyectoPokemon/img/rivales/teamrocket.png"),
    WULFRIC(47, "Wulfric", "C:/ProyectoPokemon/img/rivales/wulfric.png"),
    FABA(48, "Faba", "C:/ProyectoPokemon/img/rivales/faba.png"),
    CRASHERWAKE(49, "Crasherwake", "C:/ProyectoPokemon/img/rivales/crasherwake.png");
	//JAVIER(50, "Javier", "C:/ProyectoPokemon/img/rivales/javier.png");

    private final String nombre;
    private final String ruta;
    private final int id;

    Rivales(int id, String nombre, String ruta) {
        this.nombre = nombre;
        this.ruta = ruta;
        this.id = id;
    }
    
    public static Rivales buscarPorId(int id) {
        for (Rivales rival : Rivales.values()) {
            if (rival.id == id) {
                return rival;
            }
        }
        throw new IllegalArgumentException("No se encontro un entrenador con el id: " + id);
    }

    public String getNombre() {
        return nombre;
    }

    public String getRuta() {
        return ruta;
    }
    
    public int getId() {
        return id;
    }
}
