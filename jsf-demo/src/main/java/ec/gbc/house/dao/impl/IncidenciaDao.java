package ec.gbc.house.dao.impl;

import ec.gbc.house.converters.Estado;
import ec.gbc.house.dao.IEmpleadoDao;
import ec.gbc.house.dao.IIncidenciaDao;
import ec.gbc.house.dao.ResourceConexion;
import ec.gbc.house.entidades.Empleado;
import ec.gbc.house.entidades.Empleado_;
import ec.gbc.house.entidades.Incidencia;
import ec.gbc.house.entidades.Incidencia_;
import ec.gbc.house.enumeraciones.EstadoIncidencia;
import ec.gbc.house.enumeraciones.EstadoRegistro;
import ec.gbc.house.excepciones.SQLException;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Stateless
public class IncidenciaDao extends ResourceConexion implements IIncidenciaDao {


    @Override
    public List<Incidencia> buscarIncidenciasPorEstado(EstadoIncidencia estadoIncidencia) throws SQLException {
        try {
            CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
            CriteriaQuery<Incidencia> cq = cb.createQuery(Incidencia.class);
            Root<Incidencia> root = cq.from(Incidencia.class);
            cq.where( cb.equal(root.get(Incidencia_.estado), EstadoRegistro.ACTIVO),
                    cb.equal(root.get(Incidencia_.estadoIncidencia), estadoIncidencia));
            return this.entityManager.createQuery(cq).getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public List<Incidencia> buscarIncidencias() throws SQLException {
        try {
            CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
            CriteriaQuery<Incidencia> cq = cb.createQuery(Incidencia.class);
            Root<Incidencia> root = cq.from(Incidencia.class);
            cq.where( cb.equal(root.get(Incidencia_.estado), EstadoRegistro.ACTIVO));
            return this.entityManager.createQuery(cq).getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }
}
