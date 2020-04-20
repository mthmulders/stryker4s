package stryker4s.config.implicits

import java.nio.file.Path

import better.files.File
import pureconfig.ConfigReader
import stryker4s.config._
import pureconfig.generic.semiauto.deriveEnumerationReader
import pureconfig.BasicReaders._
import pureconfig.CollectionReaders._

trait ConfigReaderImplicits {

  implicit protected[config] val thresholdsReader: ConfigReader[Thresholds] =
    ConfigReader.forProduct3("high", "low", "break")(Thresholds(_, _, _))

  implicit protected[config] val dashboardOptionsReader: ConfigReader[DashboardOptions] = ConfigReader.forProduct5(
    "baseUrl",
    "reportType",
    "project",
    "version",
    "module"
  )(DashboardOptions(_, _, _, _, _))

  implicit protected[config] val configReader: ConfigReader[Config] =
    ConfigReader.forProduct7("mutate", "baseDir", "reporters", "files", "excludedMutations", "thresholds", "dashboard")(
      Config(_, _, _, _, _, _, _)
    )

  /** Converts a [[java.nio.file.Path]] to a [[better.files.File]] so PureConfig can read it
    *
    */
  implicit protected[config] val toFileReader: ConfigReader[File] =
    ConfigReader[Path] map (p => File(p))

  implicit protected[config] val toReporterList: ConfigReader[ReporterType] =
    deriveEnumerationReader[ReporterType]

  implicit protected[config] val toDashboardOptions: ConfigReader[DashboardReportType] =
    deriveEnumerationReader[DashboardReportType]

  implicit protected[config] val exclusions: ConfigReader[ExcludedMutations] =
    ConfigReader[List[String]] map (exclusions => ExcludedMutations(exclusions.toSet))
}
