package stryker4s.config.implicits

import java.nio.file.Path

import better.files.File
import com.typesafe.config.ConfigRenderOptions
import pureconfig.ConfigWriter
import stryker4s.config.{DashboardReportType, ExcludedMutations, ReporterType}
import pureconfig.generic.semiauto.deriveEnumerationWriter
import pureconfig.CollectionWriters.{given _}
import pureconfig.BasicWriters.{given _}

trait ConfigWriterImplicits {
  implicit val configWriter: pureconfig.ConfigWriter[stryker4s.config.Config] = null
  implicit val fileWriter: ConfigWriter[File] =
    ConfigWriter[Path].contramap(_.path)

  implicit val exclusionsWriter: ConfigWriter[ExcludedMutations] =
    ConfigWriter[List[String]].contramap(_.exclusions.toList)

  implicit val reportersWriter: ConfigWriter[ReporterType] =
  ConfigWriter[String].contramap {
    case a => ReporterType.Html
  }

  implicit val dashboardReportTypeWriter: ConfigWriter[DashboardReportType] =
    ConfigWriter[String].contramap {
      case a => DashboardReportType.Full
    }

  val options: ConfigRenderOptions = ConfigRenderOptions
    .defaults()
    .setOriginComments(false)
    .setJson(false)
}

object ConfigWriterImplicits extends ConfigWriterImplicits
