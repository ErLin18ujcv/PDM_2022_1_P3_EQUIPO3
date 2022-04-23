package hn.edu.ujcv.PDM_2022_I_EQUIPO3.business.clases


import hn.edu.ujcv.PDM_2022_I_EQUIPO3.business.interfaces.IRolBusiness
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.dao.RolRepository
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.exceptions.BusinessException
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.exceptions.NotFoundException
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.model.Rol
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Service
import java.time.format.DateTimeFormatter
import java.util.*

@Service
class RolBusiness: IRolBusiness {
    @Autowired
    val rolRepository: RolRepository?=null
    @Throws(BusinessException::class)
    override fun getRol(): List<Rol> {
        try {
            return rolRepository!!.findAll()
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }
    @Throws(BusinessException::class, NotFoundException::class)
    override fun getRolById(idRol: Int): Rol {
        val opt: Optional<Rol>
        try {
            opt=rolRepository!!.findById(idRol)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent)
            throw NotFoundException("No se encontro el rol $idRol")
        return opt.get()
    }
    @Throws(BusinessException::class)
    override fun saveRol(rol: Rol): Rol {
        try {
            //nombre
            if (rol.nombre.trimStart().isEmpty())
                throw BusinessException("El nombre del rol no puede ir en blanco.")
            if (rol.nombre.trimStart().length < 4)
                throw BusinessException("El nombre del rol no puede ser tan corto.")
            if (rol.nombre.trimStart().length > 40)
                throw BusinessException("El nombre del rol no puede contener tantos caracteres.")

            if (!rol.nombre.matches(Regex("^[^\\d]*\$")))
                throw BusinessException("El nombre del rol no puede contener numeros.")
            //validar mayusculas
            //if (!rol.nombre.matches(Regex("^[A-ZÁÉÍÓÚ]{1}[a-záéíóú]*$")))
                //throw BusinessException("El nombre debe iniciar en mayuscula.")

            //validar espacios
            if (rol.nombre.matches(Regex("^([A-Za-z]{1,2}\\s)*([A-Za-z]|)$")))
                throw BusinessException("El nombre del rol no puede llevar espacios entre letras.")

            if (Regex("(.)\\1{2,}").find(rol.nombre) != null)
                throw BusinessException("El nombre del rol no puede contener tantos caracteres repetidas.")
            //descripcion
            if (rol.descripcion.trimStart().isEmpty())
                throw BusinessException("La descripcion del rol no puede ir en blanco.")
            if (rol.descripcion.trimStart().length < 10)
                throw BusinessException("La descripcion del rol no puede ser tan corta.")
            if (rol.descripcion.trimStart().length > 100)
                throw BusinessException("La descripcion del rol no puede contener tantos caracteres.")
            if (rol.descripcion.matches(Regex("^[\\d ]*\$")))
                throw BusinessException("La descripcion del rol no puede contener solo numeros.")
            //validar espacios
            if (rol.descripcion.matches(Regex("^([A-Za-z]{1,2}\\s)*([A-Za-z]|)$")))
                throw BusinessException("La descripcion del rol no puede contener espacios entre letras.")



            if (Regex("(.)\\1{2,}").find(rol.descripcion) != null)
                throw BusinessException("La descripcion del rol no puede contener tantos caracteres repetidas.")
            return rolRepository!!.save(rol)
        }catch (e:DataIntegrityViolationException){
            throw BusinessException("Ya existe un rol con ese nombre.")
        }catch (e:Exception){
            throw BusinessException(e.message)
        }

    }
    @Throws(BusinessException::class)
    override fun saveRol(roles: List<Rol>): List<Rol> {
        try {
            for (rol in roles){
                //nombre
                if (rol.nombre.trimStart().isEmpty())
                    throw BusinessException("El nombre del rol no puede ir en blanco.")
                if (rol.nombre.trimStart().length < 4)
                    throw BusinessException("El nombre del rol no puede ser tan corto.")
                if (rol.nombre.trimStart().length > 40)
                    throw BusinessException("El nombre del rol no puede contener tantos caracteres.")
                if (!rol.nombre.matches(Regex("^[^\\d]*\$")))
                    throw BusinessException("El nombre del rol no puede contener numeros.")
                if (Regex("(.)\\1{2,}").find(rol.nombre) != null)
                    throw BusinessException("El nombre del rol no puede contener tantos caracteres repetidas.")
                //descripcion
                if (rol.descripcion.trimStart().isEmpty())
                    throw BusinessException("La descripcion del rol no puede ir en blanco.")
                if (rol.descripcion.trimStart().length < 10)
                    throw BusinessException("La descripcion del rol no puede ser tan corta.")
                if (rol.descripcion.trimStart().length > 100)
                    throw BusinessException("La descripcion del rol no puede contener tantos caracteres.")
                if (rol.descripcion.matches(Regex("^[\\d ]*\$")))
                    throw BusinessException("La descripcion del rol no puede contener solo numeros.")
                if (Regex("(.)\\1{2,}").find(rol.descripcion) != null)
                    throw BusinessException("La descripcion del rol no puede contener tantos caracteres repetidas.")
            }
            return rolRepository!!.saveAll(roles)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }

    }
    @Throws(BusinessException::class,NotFoundException::class)
    override fun updateRol(rol: Rol): Rol {
        val opt: Optional<Rol>
        try {
            opt=rolRepository!!.findById(rol.id)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent)
            throw NotFoundException("No se encontro el rol con id: ${rol.id}")
        else{
            try {
                //nombre
                if (rol.nombre.trimStart().isEmpty())
                    throw BusinessException("El nombre del rol no puede ir en blanco.")
                if (rol.nombre.trimStart().length < 4)
                    throw BusinessException("El nombre del rol no puede ser tan corto.")
                if (rol.nombre.trimStart().length > 40)
                    throw BusinessException("El nombre del rol no puede contener tantos caracteres.")
                if (!rol.nombre.matches(Regex("^[^\\d]*\$")))
                    throw BusinessException("El nombre del rol no puede contener numeros.")
                if (Regex("(.)\\1{2,}").find(rol.nombre) != null)
                    throw BusinessException("El nombre del rol no puede contener tantos caracteres repetidas.")
                //descripcion
                if (rol.descripcion.trimStart().isEmpty())
                    throw BusinessException("La descripcion del rol no puede ir en blanco.")
                if (rol.descripcion.trimStart().length < 10)
                    throw BusinessException("La descripcion del rol no puede ser tan corta.")
                if (rol.descripcion.trimStart().length > 100)
                    throw BusinessException("La descripcion del rol no puede contener tantos caracteres.")
                if (rol.descripcion.matches(Regex("^[\\d ]*\$")))
                    throw BusinessException("La descripcion del rol no puede contener solo numeros.")
                if (Regex("(.)\\1{2,}").find(rol.descripcion) != null)
                    throw BusinessException("La descripcion del rol no puede contener tantos caracteres repetidas.")
                rolRepository!!.save(rol)
            }catch (e1:DataIntegrityViolationException){
                throw BusinessException("Ya existe un rol con ese nombre")
            }catch (e : Exception){
                throw BusinessException(e.message)
            }
        }
        return opt.get()
    }
    @Throws(BusinessException::class,NotFoundException::class)
    override fun getRolByNombre(nombre: String): Rol {
        val opt: Optional<Rol>
        try {
            opt=rolRepository!!.findByNombre(nombre)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent)
            throw NotFoundException("No se encontro el rol $nombre")
        return opt.get()

    }
}