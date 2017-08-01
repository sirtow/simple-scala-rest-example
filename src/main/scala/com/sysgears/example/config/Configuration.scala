package com.sysgears.example.config

import com.typesafe.config.ConfigFactory
import util.Try

/**
 * Holds service configuration settings.
 */
trait Configuration {

  /**
   * Application config object.
   */
  val config = ConfigFactory.load()

  /** Host name/address to start service on. */
  lazy val serviceHost = Try(config.getString("service.host")).getOrElse("localhost")

  /** Port to start service on. */
  lazy val servicePort = Try(config.getInt("service.port")).getOrElse(8080)

  /** Database host name/address. */
  lazy val dbHost = Try(sys.env("db.host")).getOrElse(
    Try(config.getString("db.host")).getOrElse("localhost")
  )


  /** Database host port number. */
  lazy val dbPort = Try(sys.env("db.port")).getOrElse(
    Try(config.getInt("db.port")).getOrElse(3306)
  )

  /** Service database name. */
  lazy val dbName = Try(sys.env("db.name")).getOrElse(
    Try(config.getString("db.name")).getOrElse("rest")
  )

  /** User name used to access database. */
  lazy val dbUser = Try(sys.env("db.user")).getOrElse(
    Try(config.getString("db.user")).toOption.orNull
  )
  /** Password for specified user and database. */
  lazy val dbPassword = Try(sys.env("db.password")).getOrElse(
    Try(config.getString("db.password")).toOption.orNull
  )
}
