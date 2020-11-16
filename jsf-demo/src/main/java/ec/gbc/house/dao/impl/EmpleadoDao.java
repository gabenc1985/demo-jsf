package ec.gbc.house.dao.impl;

import ec.gbc.house.dao.IEmpleadoDao;
import ec.gbc.house.dao.ResourceConexion;
import ec.gbc.house.entidades.Empleado;
import ec.gbc.house.entidades.Empleado_;
import ec.gbc.house.enumeraciones.EstadoRegistro;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Stateless
public class EmpleadoDao extends ResourceConexion implements IEmpleadoDao  {


    @Override
    public List<Empleado> buscarEmpleadosPorEstadoAndUsername(EstadoRegistro estado, String username) {
        try {
            CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
            CriteriaQuery<Empleado> cq = cb.createQuery(Empleado.class);
            Root<Empleado> root = cq.from(Empleado.class);
            cq.where(cb.like(cb.upper(root.get(Empleado_.username)), "%".concat(username.toUpperCase()).concat("%")),
                    cb.equal(root.get(Empleado_.estado), estado));
            return this.entityManager.createQuery(cq).getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }
}
