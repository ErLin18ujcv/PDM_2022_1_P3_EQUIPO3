package hn.edu.ujcv.PDM_2022_I_EQUIPO3.model

import javax.persistence.*

@Entity
@Table(name = "laboratorio")
data class Laboratorio(val nombre:String = "", val direccion:String = ""){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0
}
