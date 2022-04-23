package hn.edu.ujcv.PDM_2022_I_EQUIPO3.model

import javax.persistence.*

@Entity
@Table(name = "fabricante_vacuna")
data class FabricanteVacuna(val nombre: String){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0
}
