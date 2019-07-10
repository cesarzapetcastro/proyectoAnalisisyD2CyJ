package entidades;

import entidades.Bodegasql;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2019-07-10T10:29:41", comments="EclipseLink-2.7.4.v20190115-rNA")
@StaticMetamodel(Proveedorsql.class)
public class Proveedorsql_ { 

    public static volatile SingularAttribute<Proveedorsql, String> tipo;
    public static volatile SingularAttribute<Proveedorsql, String> direccion;
    public static volatile SingularAttribute<Proveedorsql, Integer> idProveedorsql;
    public static volatile SingularAttribute<Proveedorsql, String> telefono;
    public static volatile SingularAttribute<Proveedorsql, String> nombre;
    public static volatile SingularAttribute<Proveedorsql, String> observacion;
    public static volatile CollectionAttribute<Proveedorsql, Bodegasql> bodegasqlCollection;

}