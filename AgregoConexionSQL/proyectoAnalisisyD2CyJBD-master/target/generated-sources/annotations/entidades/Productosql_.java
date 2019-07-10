package entidades;

import entidades.Bodegasql;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2019-07-10T10:29:41", comments="EclipseLink-2.7.4.v20190115-rNA")
@StaticMetamodel(Productosql.class)
public class Productosql_ { 

    public static volatile SingularAttribute<Productosql, String> marca;
    public static volatile SingularAttribute<Productosql, String> descipcion;
    public static volatile SingularAttribute<Productosql, Integer> idProducto;
    public static volatile SingularAttribute<Productosql, String> nombre;
    public static volatile CollectionAttribute<Productosql, Bodegasql> bodegasqlCollection;

}