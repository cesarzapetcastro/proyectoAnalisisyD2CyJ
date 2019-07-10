package entidades;

import entidades.Productosql;
import entidades.Proveedorsql;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2019-07-10T10:29:41", comments="EclipseLink-2.7.4.v20190115-rNA")
@StaticMetamodel(Bodegasql.class)
public class Bodegasql_ { 

    public static volatile SingularAttribute<Bodegasql, Productosql> productosqlidProducto;
    public static volatile SingularAttribute<Bodegasql, Integer> idBodega;
    public static volatile SingularAttribute<Bodegasql, Date> fechaDeEntrada;
    public static volatile SingularAttribute<Bodegasql, Date> fechaDeCaducidad;
    public static volatile SingularAttribute<Bodegasql, Integer> cantidad;
    public static volatile SingularAttribute<Bodegasql, Proveedorsql> proveedorsqlidProveedorsql;

}